package uk.gov.nationalarchives.tre
import com.amazonaws.services.lambda.runtime.events.LambdaDestinationEvent
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.PublishRequest
import java.util

class Lambda() extends RequestHandler[LambdaDestinationEvent, Unit] {

  private lazy val region = Region.EU_WEST_2
  private lazy val topicOption = sys.env.get("DA_EVENTBUS_TOPIC_ARN")

  override def handleRequest(event: LambdaDestinationEvent, context: Context): Unit = {
    val logger = context.getLogger
    logger.log(s"Received event at success destination\n")
    val message = event.getResponsePayload.toString

    topicOption match {
      case Some(eventbusTopic) =>
        context.getLogger.log(s"Publishing message to event bus topic: $message")
        val snsClient = SnsClient.builder().region(region).build()
        val request =
          PublishRequest.builder.message(message).topicArn(eventbusTopic).build
        snsClient.publish(request)
      case None =>
        context.getLogger.log("No event bus topic set")
    }
  }

}
