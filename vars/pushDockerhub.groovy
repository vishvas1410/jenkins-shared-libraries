def call(Map config = [:]) {

    def image       = config.get('image', 'vishvas1410/notes-app')
    def tag         = config.get('tag', 'latest')
    def credentials = config.get('credentialsId', 'dockerHubCred')

    echo "Pushing Docker image: ${image}:${tag}"

    withCredentials([usernamePassword(
        credentialsId: credentials,
        usernameVariable: 'DOCKER_USER',
        passwordVariable: 'DOCKER_PASS'
    )]) {

        sh """
            echo "\$DOCKER_PASS" | docker login -u "\$DOCKER_USER" --password-stdin
            docker push ${image}:${tag}
        """
    }

    echo '✅ Docker image pushed successfully.'
}
