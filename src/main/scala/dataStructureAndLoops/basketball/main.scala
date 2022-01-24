/** WARNING: this is a non-working code.
 * 
 * Problem statement:
 *  Given the position of a basketball, a shot direction, and it's force,
 *  is the ball going to go through the hoop?
 */
package dataStructureAndLoops.basketball



object main {
  case class Position(x: Double, y: Double){
    def distanceTo(that: Position): Double = {
      ???
    }

    def distanceToLine(line: (Position, Position)): Double = {
      ???
    }
  }

  object Position {
    val player = Position(x=0, y=1.80)
    val hoop = Position(x=6.75, y=3.048)
  }

  case class Angle(radians: Double)
  case class Speed(metersPerSecond: Double)

  /** Determines whether a shot will go through the hoops or not.
   *
   *  We calculate the position of the ball at each moment in time.
   *  If one of those position passes through the hoops, return true.
   *  If the ball ends up too far or too short, return false.
   *
   *  We compute distance between the center of the hoop and the straight
   *  segment made of two successive positions to check whether the ball
   *  passed through the hoops or not.
   */
  def isWinningShot(angle: Angle, speed: Speed): Boolean = {
    // initial horizontal and vertical speed of the ball
    val v0x = speed.metersPerSecond * math.cos(angle.radians)
    val v0y = speed.metersPerSecond * math.sin(angle.radians)

    // initial position of the ball
    val p0x = Position.player.x
    val p0y = Position.player.y

    // gravitational pull
    val g = -9.81

    def goesThroughHoop(line: (Position, Position)): Boolean = {
      Position.hoop.distanceToLine(line) < 0.01
    }

    def isNotTooFar(position: Position): Boolean = {
      (position.y > 0) && (position.x <= Position.hoop.x + 0.01)
    }

    def position(time: Int): Position = {
      val x = p0x + v0x * time
      val y = p0y + v0y * time + 0.5 * g * time * time

      Position(x,y)
    }

    // calculate position of the ball at each time by looping through a LazyList.
    val timings = LazyList.from(0) // this creates an infinite LazyList 0,1,2,3,...
    val positions = timings.map(position)

    // create trajectory line by pairing for each position
    // by pairing it with it's subsequent position
    val trajectoryLine = positions.zip(positions.tail)

    // limits trajectoryLine calculation
    trajectoryLine.takeWhile((p1, _) => isNotTooFar(p1))
    // check whether the trajectory goes through the hoop or not.
      .exists(line => goesThroughHoop(line)) 
  }

  def main(args: Array[String]): Unit = {
    val angle = Angle(1.4862)
    val speed = Speed(20)
    println(isWinningShot(angle, speed))
  }
}
