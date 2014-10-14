package com.gvolpe.users.routing

import akka.actor._
import spray.http.StatusCode
import spray.http.StatusCodes._
import spray.routing.RequestContext
import akka.actor.OneForOneStrategy
import scala.concurrent.duration._
import com.gvolpe.users._
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait PerRequest extends Actor with SprayJsonSupport {

/*

  import context._
  //val json4sFormats = DefaultJsonProtocol
  
  def r: RequestContext
  def target: ActorRef
  def message: RestMessage
  
  setReceiveTimeout(2.seconds)
  target ! message
  
  def receive = {
    case res: RestMessage => complete(OK, res)
    case v: Validation => complete(BadRequest, v)
    case ReceiveTimeout => complete(GatewayTimeout, Error("Request timeout"))
  }
  
  def complete[T <: AnyRef](status: StatusCode, obj: T) = {
    r.complete(status, obj)
    stop(self)
  }
  
  override val supervisorStrategy =
    OneForOneStrategy() {
      case e => {
        complete(InternalServerError, Error(e.getMessage))
        Stop
      }
    }

*/

}

object PerRequest {
  /*case class WithActorRef(r: RequestContext, target: ActorRef, message: RestMessage) extends PerRequest
  
  case class WithProps(r: RequestContext, props: Props, message: RestMessage) extends PerRequest {
    lazy val target = context.actorOf(props)
  }*/
}

trait PerRequestCreator {
  /*
this: Actor =>
  
  def perRequest(r: RequestContext, target: ActorRef, message: RestMessage) =
    context.actorOf(Props(new WithActorRef(r, target, message)))

  def perRequest(r: RequestContext, props: Props, message: RestMessage) =
    context.actorOf(Props(new WithProps(r, props, message)))
*/
}
