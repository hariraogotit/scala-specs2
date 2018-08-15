package farm

import org.specs2.mutable.Specification

/**
  * Created by Hari Rao on 15/08/18.
  */

// we have picked up mutable Specification to begin with but immutable Specification are much more sophisticated
class FarmSpec extends Specification{

  "Farm" should {
    val farm = new Farm("My Farm")

    "extract the proper task for the chicken"  in{
      val chicken = Animal("Bob", 12, Chicken)
      farm.taskForTheDay(Seq(chicken)) must_== Seq(FarmTask(chicken, "chicken for eggs"))
    }

    "extract the proper task for the cow"  in{
      val cow = Animal("Bessy", 12, Cow)
      farm.taskForTheDay(Seq(cow)) must_== Seq(FarmTask(cow, "milking"))
    }

    "extract the proper task for the horse"  in{
      val horse = Animal("Douglas", 12, Horse)
      farm.taskForTheDay(Seq(horse)) must_== Seq(FarmTask(horse, "plowing"))
    }

    "extract the proper task for a dog or cat"  in{
      val dog = Animal("Cleo", 12, Dog)
      val cat = Animal("Santiago", 12, Cat)
      // both checks means the same. Using beEmpty gives readable error message when the test fails
      farm.taskForTheDay(Seq(dog)) must_== Seq()
      farm.taskForTheDay(Seq(cat)) must beEmpty
    }

    "extract the proper task for multiple animals"  in{
      val chicken = Animal("Bob", 12, Chicken)
      val cow = Animal("Bessy", 12, Cow)
      val horse = Animal("Douglas", 12, Horse)
      farm.taskForTheDay(Seq(chicken, cow, horse)) must_== Seq(
                                                                FarmTask(chicken, "chicken for eggs"),
                                                                FarmTask(cow, "milking"),
                                                                FarmTask(horse, "plowing")
                                                              )
    }


  }

}
