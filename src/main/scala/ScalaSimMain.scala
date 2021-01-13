import scalAgent.{ActivityGenerator, Agent}
import scalaSim.{Context, DESimTest, Experiment, Model}

object ScalaSimMain extends App {
  println("Test")
  DESimTest()

  println("--------")

  new Experiment {
    params = Map("stopTime" -> 30)
    new Context {
      model = new Model {
        add(new Agent{agentType = "Normative "
          var x : Int = 0
          object NormRG extends ActivityGenerator

          val increment = NormRG.Gen(true) {
            println("Normative Agent " + agentType + "--- increment")
          } (false)

          val decrement = NormRG.Gen(true) {
            println("Normative Agent " + agentType + "--- decrement")
          } (false)

          addActivity(increment)
          addActivity(decrement)})
      }
    }
  }.simulate()
}
