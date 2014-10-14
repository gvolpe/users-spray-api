package com.gvolpe.users.parse

import akka.actor.Actor
import com.gvolpe.users._

class ParseInstallationActor extends Actor {

  def receive = {
    /*case RegisterUserDevice(userDevice) =>
      println("User device POST from ACTOR >> " + userDevice) */
    case _ => throw new RuntimeException("ParseInstallationActor >> Message not recognized...") 
  }
  
}
