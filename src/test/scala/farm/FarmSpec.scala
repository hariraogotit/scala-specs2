package farm

import org.specs2.matcher.Matcher
import org.specs2.mutable.Specification

/**
  * Created by Hari Rao on 15/08/18.
  */

// we have picked up mutable Specification to begin with but immutable Specification are much more sophisticated
class FarmSpec extends Specification{

  "Farm" should {
    val farm = new Farm("My Farm")

    def performATask(description: String):Matcher[FarmTask] ={
      task: FarmTask => task.description == description
    }

    "extract the proper task for the chicken"  in{
      val chicken = Animal("Bob", 12, Chicken)
      farm.taskForTheDay(Seq(chicken)) must_== Seq(FarmTask(chicken, "chicken for eggs", 12))
      farm.taskForTheDay(Seq(chicken)).head must performATask("chicken for eggs")
    }

    "extract the proper task for the cow"  in{
      val cow = Animal("Bessy", 12, Cow)
      farm.taskForTheDay(Seq(cow)) must_== Seq(FarmTask(cow, "milking", 12))
    }

    "extract the proper task for the horse"  in{
      val horse = Animal("Douglas", 12, Horse)
      farm.taskForTheDay(Seq(horse)) must_== Seq(FarmTask(horse, "plowing", 12))
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
                                                                FarmTask(chicken, "chicken for eggs",12),
                                                                FarmTask(cow, "milking",12),
                                                                FarmTask(horse, "plowing",12)
                                                              )
      farm.taskForTheDay(Seq(chicken, cow, horse)) must have size(3)
    }


    "extract the proper task for multiple animals although the order is different"  in{
      val chicken = Animal("Bob", 12, Chicken)
      val cow = Animal("Bessy", 12, Cow)
      val horse = Animal("Douglas", 12, Horse)
      // order of FarmTask is not checked but it checks that all the three animals are present
      farm.taskForTheDay(Seq(chicken, cow, horse)) must contain (
        FarmTask(cow, "milking",12),
        FarmTask(horse, "plowing", 12),
        FarmTask(chicken, "chicken for eggs", 12)
      )

    }

    "extract the proper task  descriptions for multiple animals"  in{
      val chicken = Animal("Bob", 12, Chicken)
      val cow = Animal("Bessy", 12, Cow)
      val horse = Animal("Douglas", 12, Horse)
      farm.taskForTheDay(Seq(chicken, cow, horse)) must beLike{
        case Seq(FarmTask(_, chickenDescription,_), FarmTask(_, cowDescription,_), FarmTask(_,horseDescription,_)) =>
          chickenDescription must_== "chicken for eggs"
          cowDescription must_== "milking"
          horseDescription must_== "plowing"
      }

    }

    "assigns ages as the duration of each task"  in{
      val chicken = Animal("Bob", 12, Chicken)
      val cow = Animal("Bessy", 12, Cow)
      val horse = Animal("Douglas", 12, Horse)
      farm.taskForTheDay(Seq(chicken)).map(_.duration) must contain(12)
      farm.taskForTheDay(Seq(cow)).map(_.duration) must contain(be_>(11))
      farm.taskForTheDay(Seq(horse)).map(_.duration) must contain(be_<=(13))

    }

    "assigns animal as the animal of each task"  in{
      val chicken = Animal("Bob", 12, Chicken)

      farm.taskForTheDay(Seq(chicken)).map(_.animal) must contain(beAnInstanceOf[Animal])
    }

    "throws an exception if trying to find the task for a wolf"  in{
      val wolf = Animal("Jack", 12, Wolf)

      farm.taskForTheDay(Seq(wolf)) must throwAn[Exception]
    }


  }

}
