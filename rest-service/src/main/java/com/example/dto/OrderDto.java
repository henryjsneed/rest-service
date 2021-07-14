package com.example.dto;

import java.util.List;

import com.example.model.OrderItems;

import lombok.Builder;


@Builder
public class OrderDto {
	private List<OrderItems> orderLineItemsList;

	public List<OrderItems> getOrderLineItemsList() {
		return orderLineItemsList;
	}

	public void setOrderLineItemsList(List<OrderItems> orderLineItemsList) {
		this.orderLineItemsList = orderLineItemsList;
	}

	public OrderDto(List<OrderItems> orderLineItemsList) {
		super();
		this.orderLineItemsList = orderLineItemsList;
	}
	public OrderDto() {}
}
