package com.gvolpe.users.routing

import akka.actor._
import spray.http._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol
import spray.routing._
import MediaTypes._
import com.gvolpe.users._

object JsonImplicitProtocol extends DefaultJsonProtocol {
  implicit val UserDeviceFormat = jsonFormat3(UserDevice)
}

class InstallationRouting extends HttpService with Actor with PerRequestCreator {
  
  implicit def actorRefFactory = context
  def receive = runRoute(route)

  val route = {
    
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
                  "register in parse and mongo"
                }
              }
            }
          }
        }
      }
    }
    
  }
  
}
