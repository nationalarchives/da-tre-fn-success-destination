package uk.gov.nationalarchives.tre

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.api.client.logging.{LambdaContextLogger, StdOutLogSink}
import com.amazonaws.services.lambda.runtime.events.LambdaDestinationEvent
import java.util
import org.scalatest.flatspec._
import org.scalatestplus.mockito.MockitoSugar
import org.mockito.Mockito.when

class LambdaSpec extends AnyFlatSpec with MockitoSugar {

  "The lambda" should "run without throwing exception" in {
    val lambda = new Lambda()
    val event = new util.HashMap[String, AnyRef]();
    event.put("properties", """{
            "messageType": "uk.gov.nationalarchives.tre.messages.courtdocumentpackage.available.CourtDocumentPackageAvailable",
            "timestamp": "2023-07-12T12:50:43.578017Z",
            "function": "pte-mk-tre-court-document-pack-lambda",
            "producer": "TRE",
            "executionId": "93ae2183-9d4a-4648-af35-dc1558b5f397",
            "parentExecutionId": "8c99a076-1a8e-47be-b53a-7aef6bb059a0"
          }""")
    event.put("parameters", """{
            "status": "COURT_DOCUMENT_PARSE_NO_ERRORS",
            "reference": "FCL-MK-Y1",
            "s3Bucket": "pte-mk-tre-court-document-pack-out",
            "s3Key": "FCL-MK-Y1/93ae2183-9d4a-4648-af35-dc1558b5f397/FCL-MK-Y1.tar.gz",
            "metadataFilePath": "/metadata.json",
            "metadataFileType": "Json"
        }""")
    val mockContext = mock[Context]
    when(mockContext.getLogger).thenReturn(new LambdaContextLogger(new StdOutLogSink))
    val lde = LambdaDestinationEvent.builder().withResponsePayload(event).build()
    lambda.handleRequest(lde, mockContext)
  }
}
