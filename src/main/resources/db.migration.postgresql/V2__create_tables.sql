SET client_encoding = 'UTF8';
SET TIME ZONE 'America/Sao_Paulo';

-- Cria sequence message_seq
CREATE SEQUENCE public.matricula_seq;

-- Cria tabela message
CREATE TABLE public."matricula"
(
    id               bigint                  NOT NULL,
    CONSTRAINT message_pkey PRIMARY KEY (id)
);