def call(Map config = [:]) {

    def imageName     = config.get('imageName')
    def imageTag      = config.get('tag', 'latest')
    def credentialsId = config.get('credentialsId', 'dockerHubCred')

    if (!imageName) {
        error "❌ imageName is required for push.groovy"
    }

    echo 'Pushing Docker image to Docker Hub...'

    withCredentials([usernamePassword(
        credentialsId: credentialsId,
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {

        sh """
            echo "\$DOCKER_PASS" | docker login -u "\$DOCKER_USER" --password-stdin
            docker tag ${imageName}:${imageTag} \$DOCKER_USER/${imageName}:${imageTag}
            docker push \$DOCKER_USER/${imageName}:${imageTag}
        """
    }

    echo '✅ Docker image pushed successfully.'
}
