-- telefone
INSERT INTO public.telefone(
	dataalteracao, datainclusao, id, codigodearea, numero)
	VALUES (now(), now(), 1, 63, 41324856);

    INSERT INTO public.telefone(
	dataalteracao, datainclusao, id, codigodearea, numero)
	VALUES (now(), now(), 2, 63, 2345345636);

    INSERT INTO public.telefone(
	dataalteracao, datainclusao, id, codigodearea, numero)
	VALUES (now(), now(), 3, 63, 234523452);

-- cliente
INSERT INTO public.cliente(
	dataalteracao, datainclusao, id, telefone_id, cpf, login, nome, senha)
	VALUES (now(), now(), 1, 1, '345634733', 'kk', 'kk', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==');

	INSERT INTO public.cliente(
	dataalteracao, datainclusao, id, telefone_id, cpf, login, nome, senha)
	VALUES (now(), now(), 2, 2, '345634733', 'pp', 'pp', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==');

	INSERT INTO public.cliente(
	dataalteracao, datainclusao, id, telefone_id, cpf, login, nome, senha)
	VALUES (now(), now(), 3, 3, '345634733', 'lucas', 'lucas', 'TRwn0XU29Gwl2sagG00bvjrNJvLuYo+dbOBJ7R3xFpU4m/FAUc5q8OoGbVNwPF7F5713RaYkN4qyufNCDHm/mA==');


-- perfil

INSERT INTO public.perfis(
	id_usuario, perfil)
	VALUES (1, 'ADMIN');

	INSERT INTO public.perfis(
	id_usuario, perfil)
	VALUES (2, 'CLIENTE');

	INSERT INTO public.perfis(
	id_usuario, perfil)
	VALUES (3, 'USER');

	