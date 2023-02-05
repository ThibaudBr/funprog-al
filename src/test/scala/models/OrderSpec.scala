package models

import fr.esgi.al.funprog.models.Order
import org.scalatest.funspec.AnyFunSpec

class OrderSpec extends AnyFunSpec {

  describe("Order") {
    it("should be initialized with a Left") {
      val order = Order('G')

      assert(order.order == 'G')
    }

    it("should be initialized with a Right") {
      val order = Order('D')

      assert(order.order == 'D')
    }

    it("should be initialized with a Forward") {
      val order = Order('A')

      assert(order.order == 'A')
    }

    it("should return string representation") {
      val order = Order('A')

      assert(order.toString == "A")
    }
  }
}
