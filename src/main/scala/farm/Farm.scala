package farm

/**
  * Created by Hari Rao on 15/08/18.
  */
class Farm(name: String) {

  def taskForTheDay(animals: Seq[Animal]) : Seq[FarmTask]  ={
    animals.flatMap {
      case animal @ Animal(_,age,Cow) => Seq(FarmTask(animal,"milking",age))
      case animal @ Animal(_,age,Chicken) =>  Seq(FarmTask(animal, "chicken for eggs", age))
      case animal @ Animal(_,age,Horse) => Seq(FarmTask(animal, "plowing", age))
      case animal @ Animal(_,_,Wolf) => throw new Exception("Wolves are attacking the farm!")
      case _ => Seq()
    }
  }

}
