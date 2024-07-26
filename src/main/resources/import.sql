-- telefone
INSERT INTO public.telefone(
	 codigodearea, numero)
	VALUES ('42356342', '67'),
	('65368435', '64'),
	('34562562', '62'),
	('56589556', '99');
-- cliente
INSERT INTO public.cliente(
	telefone_id, cpf, login, nome, senha)
	VALUES (1, '23452345', 'pp', 'pp', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA=='),
	(2, '23452345', 'qq', 'qq', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA=='),
	(3, '23452345', 'gg', 'gg', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA=='),
	(4, '23452345', 'gustavo', 'gustavo', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==');


-- perfil

INSERT INTO public.perfis(
	id_usuario, perfil)
	VALUES (1, 'ADMIN'),
	(2, 'CLIENTE'),
	(3, 'ADMIN'),
	(4, 'USER');
	