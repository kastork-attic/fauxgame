// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all: '*/*',
    atom: 'application/atom+xml',
    css: 'text/css',
    csv: 'text/csv',
    form: 'application/x-www-form-urlencoded',
    html: ['text/html', 'application/xhtml+xml'],
    js: 'text/javascript',
    json: ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss: 'application/rss+xml',
    text: 'text/plain',
    xml: ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.excludes = ['**/WEB-INF/**','**/META-INF/**']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart = false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
  development {
    grails.app.context = '/'
    grails.logging.jul.usebridge = true
    grails.plugin.springsecurity.cas.serviceUrl = 'http://localhost:9001/j_spring_cas_security_check'
    grails.plugin.springsecurity.cas.proxyCallbackUrl = 'http://localhost:9001/secure/receptor'

  }
  production {
    grails.app.context = '/'
    grails.logging.jul.usebridge = true
    grails.serverURL = "https://faux.globalecco.org"
    grails.plugin.springsecurity.cas.serviceUrl = 'https://faux.globalecco.org/j_spring_cas_security_check'
    grails.plugin.springsecurity.cas.proxyCallbackUrl = 'https://faux.globalecco.org/secure/receptor'

  }
  beanstalk {
    grails.app.context = '/'
    grails.logging.jul.usebridge = false
    grails.serverURL = "http://faux-game.elasticbeanstalk.com"
    grails.plugin.springsecurity.cas.serviceUrl = 'http://faux-game.elasticbeanstalk.com/j_spring_cas_security_check'
    grails.plugin.springsecurity.cas.proxyCallbackUrl = 'http://faux-game.elasticbeanstalk.com/secure/receptor'
  }
}

// log4j configuration
log4j = {
  // Example of changing the log pattern for the default console appender:
  //
  appenders {
      console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
  }

  error 'org.codehaus.groovy.grails.web.servlet',        // controllers
      'org.codehaus.groovy.grails.web.pages',          // GSP
      'org.codehaus.groovy.grails.web.sitemesh',       // layouts
      'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
      'org.codehaus.groovy.grails.web.mapping',        // URL mapping
      'org.codehaus.groovy.grails.commons',            // core / classloading
      'org.codehaus.groovy.grails.plugins',            // plugins
      'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
      'org.springframework',
      'org.hibernate',
      'net.sf.ehcache.hibernate'

//  debug 'grails.plugin.springsecurity'
//  debug 'org.codehaus.groovy.grails.plugin.springsecurity'
//  debug 'org.springframework.security'
//  debug 'org.jasig.cas.client'
  root {
    error 'stdout'
//    info 'stdout'
    warn 'stdout'
//    debug 'stdout'
    additivity = true
  }
}

grails.plugin.springsecurity.rejectIfNoRule = true
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap = [
    '/j_spring_cas_security_check': ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/secure/**': ['ROLE_ADMIN'],
    '/login/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/**/new': ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/**/play/': ['ROLE_PLAYER'],
    '/**/play/**': ['ROLE_PLAYER'],
    '/play/**': ['ROLE_PLAYER'],
    'play/**': ['ROLE_PLAYER'],
    '/**': ['ROLE_ADMIN']
]

grails.plugin.springsecurity.providerNames = ["casAuthenticationProvider"]

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'edu.nps.fauxgame.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'edu.nps.fauxgame.UserRole'
grails.plugin.springsecurity.authority.className = 'edu.nps.fauxgame.Role'


grails.plugin.springsecurity.cas.loginUri = '/login'
grails.plugin.springsecurity.cas.serverUrlPrefix = 'https://cas.nps.edu/ecco'
grails.plugin.springsecurity.cas.proxyReceptorUrl = '/secure/receptor'

// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

// GSP settings - new in 2.3.7
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}

rabbitmq {

  connection {
    host = System.getenv()['RABBIT_HOST']
    username = System.getenv()['RABBIT_USERNAME']
    password = System.getenv()['RABBIT_PASSWORD']
    virtualHost = System.getenv()['RABBIT_VHOST']
  }

  queues = {
    exchange name: "ecco.exchange", type: "topic", {

      // our endpoint to listen for new game requests
      queue name: "faux.queue", durable: true, binding: "ecco.binding.#"

      // our response queue (for, e.g., responses to profile requests
      queue name: "faux.reply.queue", durable: true, binding: "ecco.binding.#"

      // destination for information queries to lobby (for, e.g., profile)
      queue name: "lobby.query.queue", durable: false, binding: "ecco.binding.#"

      // destination for game state changes
      queue name: "lobby.update.queue", durable: false, binding: "ecco.binding.#"

    }
  }

}
