module SamMemberSpringLjava {
	exports su.spring.web.login;
	exports su.member.dto;
	exports su.spring.config;
	exports su.member.frontcontroller;
	exports su.spring.test;
	exports su.login.dao;
	exports su.member.dao;
	exports su.member.service;

	requires com.zaxxer.hikari;
	requires transitive jakarta.servlet;
	requires transitive java.sql;
	requires junit;
	requires lombok;
	requires org.mybatis;
	requires transitive org.mybatis.spring;
	requires org.slf4j;
	requires spring.beans;
	requires transitive spring.context;
	requires spring.context.support;
	requires spring.core;
	requires transitive spring.jdbc;
	requires spring.test;
	requires spring.tx;
	requires spring.web;
	requires spring.webmvc;
	
	opens configuration;
	opens mapper;
}