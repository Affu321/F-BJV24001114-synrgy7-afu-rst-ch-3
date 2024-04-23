--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-04-23 10:18:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4872 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16415)
-- Name: merchant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchant (
    id uuid NOT NULL,
    merchant_name character varying(255),
    merchant_location character varying(255),
    open boolean
);


ALTER TABLE public.merchant OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16442)
-- Name: orderdetail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderdetail (
    id uuid NOT NULL,
    order_id uuid,
    product_id uuid,
    quantity integer
);


ALTER TABLE public.orderdetail OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16432)
-- Name: orderentity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderentity (
    id uuid NOT NULL,
    order_time timestamp without time zone,
    destination_address character varying(255),
    user_id uuid,
    completed boolean
);


ALTER TABLE public.orderentity OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16422)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id uuid NOT NULL,
    product_name character varying(255),
    price numeric(10,2),
    merchant_id uuid
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16408)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id uuid NOT NULL,
    username character varying(255),
    email_address character varying(255),
    password character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 4863 (class 0 OID 16415)
-- Dependencies: 217
-- Data for Name: merchant; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchant (id, merchant_name, merchant_location, open) FROM stdin;
\.


--
-- TOC entry 4866 (class 0 OID 16442)
-- Dependencies: 220
-- Data for Name: orderdetail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderdetail (id, order_id, product_id, quantity) FROM stdin;
\.


--
-- TOC entry 4865 (class 0 OID 16432)
-- Dependencies: 219
-- Data for Name: orderentity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderentity (id, order_time, destination_address, user_id, completed) FROM stdin;
\.


--
-- TOC entry 4864 (class 0 OID 16422)
-- Dependencies: 218
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, product_name, price, merchant_id) FROM stdin;
\.


--
-- TOC entry 4862 (class 0 OID 16408)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, username, email_address, password) FROM stdin;
\.


--
-- TOC entry 4708 (class 2606 OID 16421)
-- Name: merchant merchant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant
    ADD CONSTRAINT merchant_pkey PRIMARY KEY (id);


--
-- TOC entry 4714 (class 2606 OID 16446)
-- Name: orderdetail orderdetail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail
    ADD CONSTRAINT orderdetail_pkey PRIMARY KEY (id);


--
-- TOC entry 4712 (class 2606 OID 16436)
-- Name: orderentity orderentity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderentity
    ADD CONSTRAINT orderentity_pkey PRIMARY KEY (id);


--
-- TOC entry 4710 (class 2606 OID 16426)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 4706 (class 2606 OID 16414)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4717 (class 2606 OID 16447)
-- Name: orderdetail orderdetail_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail
    ADD CONSTRAINT orderdetail_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orderentity(id);


--
-- TOC entry 4718 (class 2606 OID 16452)
-- Name: orderdetail orderdetail_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail
    ADD CONSTRAINT orderdetail_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 4716 (class 2606 OID 16437)
-- Name: orderentity orderentity_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderentity
    ADD CONSTRAINT orderentity_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 4715 (class 2606 OID 16427)
-- Name: product product_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchant(id);


-- Completed on 2024-04-23 10:18:27

--
-- PostgreSQL database dump complete
--

