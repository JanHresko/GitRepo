import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.DefaultExchange

// Load Groovy Script
GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../../src/main/groovy/XMLTransformation.groovy'))
// Initialize CamelContext and exchange for the message
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)
// Initialize the message body with the input file
def body = new File('../../../data/in/input.xml')
// Set exchange body in case Type Conversion is required
exchange.getIn().setBody(body)
msg.setBody(exchange.getIn().getBody())
// Execute script
script.processData(msg)
exchange.getIn().setBody(msg.getBody())
// Display results of script in console
println("Body:\r\n${msg.getBody(String)}")
println('Headers:')
msg.getHeaders().each { k, v -> println("$k = $v") }
println('Properties:')
msg.getProperties().each { k, v -> println("$k = $v") }
