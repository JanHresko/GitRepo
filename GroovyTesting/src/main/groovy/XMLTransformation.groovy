import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import groovy.xml.*;

def Message processData(Message message) {
    Reader reader = message.getBody(Reader)
    def Order = new XmlSlurper().parse(reader)
    Writer writer = new StringWriter()
    def indentPrinter = new IndentPrinter(writer, ' ')
    def builder = new MarkupBuilder(indentPrinter)
    builder.PurchaseOrder {
        'Header' {
            'ID' Order.Header.OrderNumber
            'DocumentDate' LocalDate.parse(Order.Header.Date.text(), DateTimeFormatter.ofPattern('yyyyMMdd')).format(DateTimeFormatter.ofPattern('yyyy-MM-dd'))
        }
        def items = Order.Item.findAll { it.Valid.text() == 'true' }
        items.each { item ->
            'Item' {
                'ItemNumber' item.ItemNumber.text().padLeft(3, '0')
                'ProductCode' item.MaterialNumber
                'Quantity' item.Quantity
            }
        }
    }
    message.setBody(writer.toString())
    return message
}
