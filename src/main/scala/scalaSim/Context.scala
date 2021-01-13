package scalaSim

import scalAgent._


import scala.collection.mutable.ListBuffer

/**
 * Created by Levent Yilmaz on 2/4/2017.
 */
trait Context  {
  var agentIdentifier : Double = 0
  var simulator : DTSim = _
  var simAgents = scala.collection.mutable.ListBuffer.empty[Agent]
  var simExperiment : Experiment = ModelUtility.experiment
  var model : Model = _
  ModelUtility.register(this)
  simExperiment.setContext(this)

  def setExperiment(e : Experiment): Unit = {
    simExperiment = e
  }

  def param(pname : String): Any = {
    simExperiment.params(pname)
  }


  def setModelName(n : String): Unit = {
    ModelUtility.setModelName(n)
  }

  def add(agent : Agent): Unit = {
    agent.agentId=agentIdentifier
    agentIdentifier=agentIdentifier+1
    simAgents += agent

  }

  def add(agent : Agent, x : Int, y : Int): Unit = {
    agent.agentId=agentIdentifier
    agentIdentifier=agentIdentifier+1
    simAgents += agent
  }

  def remove(agent: Agent): Unit = {
    simAgents -= agent
    simulator remove agent
  }

  def simulate(): Unit = {
    initSim(param("stopTime").asInstanceOf[Int]).run()
  }

  private def initSim(st : Int) : DTSim = {
    simulator = new DTSim {
      stopTime = st

      override def init(): Unit = {
        for (x <- simAgents) {
          var e = new EventWrapper { self =>
            target = x
            event = new Function1[Agent,Unit] {
              def apply(x:Agent): Unit = {
                x.action()
                schedule(x.interval,self)
              }
            }
          }
          schedule(x.start,e)
        }
      }
    }
    simulator
  }


}


class EventWrapper {
  var target : Agent = _
  var event : Function[Agent,Unit] = _

}

