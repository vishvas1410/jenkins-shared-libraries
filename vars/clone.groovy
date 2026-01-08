
def call(Map config = [:]) {

    def branch = config.get('branch', 'main')
    def repoUrl = config.get('repoUrl')

    if (!repoUrl) {
        error "❌ repoUrl is required for clone.groovy"
    }

    echo 'Cloning source code...'

    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        userRemoteConfigs: [[
            url: repoUrl
        ]],
        extensions: [
            [$class: 'CloneOption', shallow: true, depth: 1]
        ]
    ])

    echo '✅ Code cloned successfully.'
}
