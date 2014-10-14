package com.gvolpe.users

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import spray.http._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol
import spray.routing._
import MediaTypes._
import com.gvolpe.users.parse.ParseInstallationActor

case class UserDevice(userId: Int, deviceType: String, deviceToken: String)

object JsonImplicitProtocol extends DefaultJsonProtocol {
  implicit val UserDeviceFormat = jsonFormat3(UserDevice)
}

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class InstallationsActor extends Actor with InstallationsService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait InstallationsService extends HttpService {

  implicit val timeout = Timeout(5.seconds)
  
  val myRoute = {
    
    import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
    import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
    import JsonImplicitProtocol._
    
    get {
      path("") {
        respondWithMediaType(`text/html`) { 
          complete {
            <html>
              <body>
                <h1>Mobile User Installations API</h1>
								<h3>Resources</h3>
								<ul>
									<li>GET /users/(id)/installations</li>
									<li>POST /users/(id)/installations</li>
								</ul>
              </body>
            </html>
          }
        }
      } 
    } ~
    pathPrefix("users" / IntNumber) { userId =>
      path("installations") {
        get { ctx =>
          ctx.complete {
            // Find user device here...
          	val userDevice = UserDevice(userId, "android", "0d8b97dd-4d09-4cdf-a64b-008526a10933")
       			userDevice
          }
        } ~
        post {
          decompressRequest() {
            entity(as[UserDevice]) { userDevice =>
              detach() {
                complete {
                  "sad"
                }
              }
            }
          }
        }
      }
    }
    
  }
      
}
