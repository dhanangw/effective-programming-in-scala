package Set

import collection.immutable.List


object main {
  // Define Shape as enum
  object Shape extends Enumeration {
    type Shape = Value

    val diamond, squiggle, oval = Value
  }

  // Define Colour as enum
  object Colour extends Enumeration {
    type Colour = Value

    val red, green, purple = Value
  } 

  // Define Shading as enum
  object Shading extends Enumeration {
    type Shading = Value

    val solid, striped, open = Value
  }

  // Define NumberOfShape as enum,
  // to restrict number of cards. 
  object NumberOfShape extends Enumeration {
    type NumberOfShape = Value

    val one, two, three = Value
  }

  // Define Card as case class
  case class Card(shape: Shape.Value, colour: Colour.Value, shading: Shading.Value, numberOfShape: NumberOfShape.Value)

  def isNumberOfShapeValid(cards: List[Card]): Boolean = {
    val allSame: Boolean = cards(0).numberOfShape == cards(1).numberOfShape && cards(0).numberOfShape == cards(2).numberOfShape
    val allDifferent: Boolean = cards(0).numberOfShape != cards(1).numberOfShape && cards(0).numberOfShape != cards(2).numberOfShape && cards(1).numberOfShape != cards(2).numberOfShape
    allSame || allDifferent
  }

  def isShapeValid(cards: List[Card]): Boolean = {
    val allSame: Boolean = cards(0).shape == cards(1).shape && cards(0).shape == cards(2).shape
    val allDifferent: Boolean = cards(0).shape != cards(1).shape && cards(0).shape != cards(2).shape && cards(1).shape != cards(2).shape
    allSame || allDifferent
  }

  def isShadingValid(cards: List[Card]): Boolean = {
    val allSame: Boolean = cards(0).shading == cards(1).shading && cards(0).shading == cards(2).shading
    val allDifferent: Boolean = cards(0).shading != cards(1).shading && cards(0).shading != cards(2).shading && cards(1).shading != cards(2).shading
    allSame || allDifferent
  }

  def isColourValid(cards: List[Card]): Boolean = {
    val allSame: Boolean = cards(0).colour == cards(1).colour && cards(0).colour == cards(2).colour
    val allDifferent: Boolean = cards(0).colour != cards(1).colour && cards(0).colour != cards(2).colour && cards(1).colour != cards(2).colour
    allSame || allDifferent
  }

  // Method to check if a Set is valid
  def isSetValid(cards: List[Card]): Boolean = {
    return isNumberOfShapeValid(cards) && isShapeValid(cards) && isShadingValid(cards) && isColourValid(cards)
  }

  def main(args: Array[String]): Unit = {
    val cards = List(
      Card(Shape.diamond, Colour.red, Shading.solid, NumberOfShape.one),
      Card(Shape.squiggle, Colour.green, Shading.striped, NumberOfShape.two),
      Card(Shape.oval, Colour.purple, Shading.open, NumberOfShape.three),
    )

    println(isSetValid(cards))
  }
}

