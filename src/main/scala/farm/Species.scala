package farm

/**
  * Created by Hari Rao on 15/08/18.
  */

// following are the quick and dirty way to create enum in scala that are used to pattern match
sealed trait Species
case object Cow extends Species
case object Horse extends Species
case object Chicken extends Species
case object Dog extends Species
case object Cat extends Species
case object Wolf extends Species
