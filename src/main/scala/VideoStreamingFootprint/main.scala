package VideoStreamingFootprint


object main {
  // Model user experience as a case class.
  case class Experience(duration: Int, definition: Double, network: Network.Value)

  // Network as Enum.
  object Network extends Enumeration {
    type Network = Value

    val Fixed, Mobile = Value
  }

  val lowQualityBitrate: Double = 0.3 // MB/s
  val highQualityBitrate: Double = 0.6 // MB/s



  def networkEnergyConsumption(network: Network.Value): Double = network match {
    case Network.Fixed => 0.00043
    case Network.Mobile => 0.00088
  }

  def calculateFootPrint(experience: Experience): Unit = {
    val dataCenterEnergyConsumption = 0.000072 // KWH per MB.
    val kgCO2PerKWH = 0.5

    val videoSize = experience.duration * experience.definition
    val networkEnergy = dataCenterEnergyConsumption * networkEnergyConsumption(experience.network)

    val footprint = videoSize * networkEnergy * kgCO2PerKWH
    println(footprint)
  }

  def main(args: Array[String]): Unit = {
    val highQualityMobileFootprint = Experience(duration=60 * 30, definition=highQualityBitrate, network=Network.Mobile)
    val lowQualityFixedFootprint = Experience(duration=60 * 30, definition=lowQualityBitrate, network=Network.Fixed)

    calculateFootPrint(highQualityMobileFootprint)
    calculateFootPrint(lowQualityFixedFootprint)
  }
}
