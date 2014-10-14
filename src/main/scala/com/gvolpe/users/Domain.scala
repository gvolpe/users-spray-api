package com.gvolpe.users

trait RestMessage

case class RegisterUserDeviceToParse(userDevice: UserDevice) extends RestMessage
case class RegisterUserDeviceToMongoDb(userDevice: UserDevice) extends RestMessage
