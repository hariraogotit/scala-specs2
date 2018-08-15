package farm

/**
  * Created by Hari Rao on 15/08/18.
  */
class Farm(name: String) {

  def taskForTheDay(animals: Seq[Animal]) : Seq[FarmTask]  ={
    animals.flatMap {
      case animal @ Animal(_,_,Cow) => Seq(FarmTask(animal,"milking"))
      case animal @ Animal(_,_,Chicken) =>  Seq(FarmTask(animal, "chicken for eggs"))
      case animal @ Animal(_,_,Horse) => Seq(FarmTask(animal, "plowing"))
      case _ => Seq()
    }
  }

}
