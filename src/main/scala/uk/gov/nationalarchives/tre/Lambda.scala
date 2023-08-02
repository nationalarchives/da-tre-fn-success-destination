package uk.gov.nationalarchives.tre
import com.amazonaws.services.lambda.runtime.events.LambdaDestinationEvent
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.PublishRequest
import java.util

class Lambda() extends RequestHandler[LambdaDestinationEvent, Unit] {

  private lazy val region = Region.EU_WEST_2
  private lazy val topicOption = sys.env.get("TRE_INTERNAL_TOPIC_ARN")

  override def handleRequest(event: LambdaDestinationEvent, context: Context): Unit = {
    val logger = context.getLogger //TODO: figure out logging...
    logger.log(s"TOPIC: ${topicOption.get}")
    val payload= event.getResponsePayload
    logger.log(s"EVENT PAYLOAD: $payload\n")
    val msg = payload.toString
    logger.log(s"MSG: $msg\n")
    val snsClient = SnsClient.builder().region(region).build()
    val topic = topicOption.get // TODO: error handling this and in general...
    val request = PublishRequest.builder.message(msg).topicArn(topic).build
    snsClient.publish(request)
  }

}
