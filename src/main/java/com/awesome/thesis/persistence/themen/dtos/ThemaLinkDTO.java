package com.awesome.thesis.persistence.themen.dtos;

import org.springframework.data.relational.core.mapping.Table;

@Table("thema_link")
public record ThemaLinkDTO(String url, String text) {
}
