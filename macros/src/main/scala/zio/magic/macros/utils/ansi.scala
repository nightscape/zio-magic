package zio.magic.macros.utils

object ansi {
  trait AnsiCode {
    def code: String
  }

  val Reset = "\u001b[0m"

  sealed abstract class Color(val code: String) extends AnsiCode

  object Color {
    case object Black   extends Color(scala.Console.BLACK)
    case object Blue    extends Color(scala.Console.BLUE)
    case object Cyan    extends Color(scala.Console.CYAN)
    case object Green   extends Color(scala.Console.GREEN)
    case object Magenta extends Color(scala.Console.MAGENTA)
    case object Red     extends Color(scala.Console.RED)
    case object White   extends Color("\u001b[97m")
    case object Yellow  extends Color(scala.Console.YELLOW)
  }

  sealed abstract class Style(val code: String) extends AnsiCode

  object Style {
    case object Bold       extends Style("\u001b[1m")
    case object Faint      extends Style("\u001b[2m")
    case object Underlined extends Style("\u001b[4m")
  }

  implicit class AnsiStringOps(private val self: String) extends AnyVal {
    // Colors
    def black: String   = withAnsi(Color.Black)
    def blue: String    = withAnsi(Color.Blue)
    def cyan: String    = withAnsi(Color.Cyan)
    def green: String   = withAnsi(Color.Green)
    def magenta: String = withAnsi(Color.Magenta)
    def red: String     = withAnsi(Color.Red)
    def white: String   = withAnsi(Color.White)
    def yellow: String  = withAnsi(Color.Yellow)

    def withColor(color: Color): String = withAnsi(color)

    // Styles
    def bold: String       = withAnsi(Style.Bold)
    def faint: String      = withAnsi(Style.Faint)
    def underlined: String = withAnsi(Style.Underlined)

    private def withAnsi(ansiCode: AnsiCode) = ansiCode.code + self + scala.Console.RESET
  }
}
