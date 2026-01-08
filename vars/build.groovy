def call(Map config = [:]) {

    def buildCmd = config.get('command', 'docker compose build')

    echo 'Building Docker images...'

    sh """
        ${buildCmd}
    """

    echo '✅ Docker images built successfully.'
}
