package scalaSim

trait Experiment  {
  var expType : String = _
  var params : Map[String,Any] = _
  var ctx : Context = _
  ModelUtility.experiment = this

  def setContext(ct : Context): Unit = {
    ctx = ct
  }

  def simulate(): Unit = {
    ctx.simulate
  }

}