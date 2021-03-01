def template = 'asciidoctor-pdf'

params = '-i /home/jenkins/site-ssh-keys/id_site -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no'
host = 'site@gatling.io'

source = '/home/site/frontline/docs'
destination = '/opt/frontline/docs'

def scp(file) {
  sh("scp $params $file $host:$source/\$(basename $file)")
}

def sshCommand(command) {
  sh("ssh $params $host '$command'")
}

ansiColor('xterm') {
  podTemplate(label: template) {
    node(template) {
      stage('Checkout') {
        checkout scm
      }
      stage('Build & Publish') {
        container(template) {
          // Copy configuration to be included in Installation Guide
          sh(script: 'cp modules/app/web/main/src/main/resources/frontline-defaults.conf frontline-docs/installation-guide/')
          dir('frontline-docs') {
            // Build
            def documents = sh(script: 'find . -name "FrontLine*.adoc"', returnStdout: true).trim().split('\r?\n')
            documents.each { sh("asciidoctor-pdf --trace $it") }
            // Publish
            def pdfs = sh(script: 'find . -name "*.pdf"', returnStdout: true).trim().split('\n?\n')
            pdfs.each { scp(it) }
            // Synchronize production directories
            sshCommand("sudo rsync -Pacvz --delete $source/ $destination")
            sshCommand("sudo chown -R www-data: $destination")
          }
        }
      }
    }
  }
}
