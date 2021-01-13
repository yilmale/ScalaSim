package scalaSim

import scalAgent.Agent



/**
 * Created by Levent Yilmaz on 2/4/2017.
 */

class Model {

}

object ModelUtility {
  var experiment : Experiment = _
  var ctx : Context = _
  var modelName : String = _

  def setModelName(n : String): Unit = {
    modelName = n
  }
  def getModelName : String = {
    modelName
  }


  def register(m : Context): Unit = {
    ctx = m
  }

  def remove(a : Agent): Unit = {
    ctx remove a

  }

}