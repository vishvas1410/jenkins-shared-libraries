def call(Map config = [:]) {

    def composeCmd = config.get('command', 'docker compose up -d')
    def bringDown  = config.get('down', true)

    echo 'Deploying application via Docker Compose...'

    if (bringDown) {
        sh 'docker compose down'
    }

    sh """
        ${composeCmd}
    """

    echo '✅ Deployment completed.'
}
