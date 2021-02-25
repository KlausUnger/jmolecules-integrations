/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jmolecules.examples.jpa;

import org.jmolecules.examples.jpa.customer.Address;
import org.jmolecules.examples.jpa.customer.Customer;
import org.jmolecules.examples.jpa.customer.Customers;
import org.jmolecules.examples.jpa.order.Order;
import org.jmolecules.examples.jpa.order.Orders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Oliver Drotbohm
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);

		var address = new Address("41 Greystreet", "Dreaming Tree", "2731");

		var customers = context.getBean(Customers.class);
		var customer = customers.save(new Customer("Dave", "Matthews", address));

		var orders = context.getBean(Orders.class);
		var order = orders.save(new Order(customer));

		customers.resolveRequired(order.getCustomer());
	}
}
