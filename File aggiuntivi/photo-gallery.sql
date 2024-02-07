PGDMP      ;                 |            photogal    16.0    16.0 `    d           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            e           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            f           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            g           1262    18433    photogal    DATABASE     {   CREATE DATABASE photogal WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Italian_Italy.1252';
    DROP DATABASE photogal;
                postgres    false                        2615    18434    photogallery    SCHEMA        CREATE SCHEMA photogallery;
    DROP SCHEMA photogallery;
                postgres    false            k           1247    18436    fullname    DOMAIN     �   CREATE DOMAIN photogallery.fullname AS character varying(32)
	CONSTRAINT fullname_check CHECK (((VALUE)::text ~ '^[a-zA-Z ]*$'::text));
 #   DROP DOMAIN photogallery.fullname;
       photogallery          postgres    false    6                       1255    18438    Delete_Photo()    FUNCTION     s  CREATE FUNCTION photogallery."Delete_Photo"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

delete_test photogallery.photo.photo_code%TYPE;
photonum photogallery.photo.photo_code%TYPE;

BEGIN

SELECT PH.photo_code into delete_test
FROM photogallery.photo as PH JOIN photogallery.shared_photo as SP on SP.photo_code = PH.photo_code
WHERE PH.photo_code = OLD.photo_code;

SELECT photo_code into photonum
FROM photogallery.photo
WHERE photo_code = OLD.photo_code;

IF delete_test IS NOT NULL
THEN
UPDATE photogallery.photo
SET scope = 'Eliminated', nickname = 'Deleted User'
WHERE photo_code = OLD.photo_code;

ELSE

DELETE FROM photogallery.user_tag
WHERE (OLD.photo_code = photo_code);

DELETE FROM photogallery.photo_tag
WHERE (OLD.photo_code = photo_code);

DELETE FROM photogallery.is_in_video
WHERE (OLD.photo_code = photo_code);
RETURN OLD;
END IF;
RETURN NULL;
END;$$;
 -   DROP FUNCTION photogallery."Delete_Photo"();
       photogallery          postgres    false    6                       1255    18439    Delete_User()    FUNCTION     =  CREATE FUNCTION photogallery."Delete_User"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

fotoUtentedaEliminare CURSOR FOR
(SELECT photo_code
FROM photogallery.photo
WHERE (Nickname = OLD.Nickname) AND Photo_Code NOT IN
(SELECT PH1.Photo_Code
FROM photogallery.user_tag AS U JOIN photogallery.photo AS PH1 ON U.photo_code = PH1.photo_code
WHERE PH1.Nickname = OLD.Nickname AND U.Photo_Code = PH1.Photo_Code)
);

fotoUtentedaModificare CURSOR FOR
(SELECT photo_code
FROM photogallery.photo
WHERE (Nickname = OLD.Nickname) AND Photo_Code IN
(SELECT PH1.Photo_Code
FROM photogallery.user_tag AS U JOIN photogallery.photo AS PH1 ON U.photo_code = PH1.photo_code
WHERE PH1.Nickname = OLD.Nickname AND U.Photo_Code = PH1.Photo_Code)
);

FotodaEliminare integer;
FotodaModificare integer;

BEGIN 

OPEN fotoUtentedaModificare;

LOOP
FETCH fotoUtentedaModificare INTO FotodaModificare;
UPDATE photogallery.photo
SET nickname = 'Deleted User'
WHERE (photo_code = FotodaModificare);
IF(NOT FOUND)
THEN EXIT;
END IF;
END LOOP;
CLOSE fotoUtentedaModificare;

OPEN fotoUtentedaEliminare;

LOOP
FETCH fotoUtentedaEliminare INTO FotodaEliminare;
DELETE FROM photogallery.photo_tag
WHERE (photo_code = FotodaEliminare);
DELETE FROM photogallery.is_in_video
WHERE (photo_code = FotodaEliminare);
DELETE FROM photogallery.photo
WHERE (photo_code = FotodaEliminare);
IF(NOT FOUND)
THEN EXIT;
END IF;
END LOOP;

DELETE FROM photogallery.video
WHERE (OLD.Nickname = Nickname);

DELETE FROM photogallery.partecipating_users
WHERE (OLD.Nickname = Nickname);

CLOSE fotoUtentedaEliminare;

RETURN NULL;
END;$$;
 ,   DROP FUNCTION photogallery."Delete_User"();
       photogallery          postgres    false    6            �            1255    18923    Delete_Video()    FUNCTION     �   CREATE FUNCTION photogallery."Delete_Video"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN

DELETE FROM photogallery.is_in_video
WHERE video_code = OLD.video_code;

RETURN OLD;
END;$$;
 -   DROP FUNCTION photogallery."Delete_Video"();
       photogallery          postgres    false    6            �            1255    18440    Location_Count()    FUNCTION     e  CREATE FUNCTION photogallery."Location_Count"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

location_add photogallery.location.location_name%TYPE;

BEGIN

SELECT L.Location_Name INTO location_add
FROM photogallery.photo as PH JOIN photogallery.location as L on L.Location_Name = PH.Location_Name
WHERE PH.Location_Name = L.Location_Name AND PH.Photo_Code IN
(
SELECT PH1.Photo_Code
FROM photogallery.photo as PH1
GROUP BY (PH1.Photo_Code)
ORDER BY PH1.Photo_Code DESC
LIMIT 1);


UPDATE photogallery.location
SET photo_count = photo_count+1
WHERE (location_name = location_add);
RETURN NULL;
END;

$$;
 /   DROP FUNCTION photogallery."Location_Count"();
       photogallery          postgres    false    6            �            1255    18441    Location_Count_Subtract()    FUNCTION     �  CREATE FUNCTION photogallery."Location_Count_Subtract"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

location_add photogallery.location.location_name%TYPE;

BEGIN

SELECT L.Location_Name INTO location_add
FROM photogallery.photo as PH JOIN photogallery.location as L on L.Location_Name = PH.Location_Name
WHERE PH.Location_Name = OLD.Location_Name;


UPDATE photogallery.location
SET photo_count = photo_count-1
WHERE (location_name = location_add);
RETURN NULL;
END;

$$;
 8   DROP FUNCTION photogallery."Location_Count_Subtract"();
       photogallery          postgres    false    6            �            1255    18442    NewUser_Collection()    FUNCTION       CREATE FUNCTION photogallery."NewUser_Collection"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

add_user photogallery.photo.nickname%TYPE;

BEGIN

SELECT PH.nickname INTO add_user
FROM photogallery.photo as PH 
WHERE PH.photo_code = NEW.photo_code
AND (PH.nickname, NEW.collection_name) NOT IN
(SELECT nickname, collection_name
FROM photogallery.partecipating_users);


IF(add_user IS NOT NULL)
THEN
INSERT INTO photogallery.partecipating_users
VALUES (current_date, add_user, NEW.collection_name);
END IF;
RETURN NULL;

END;$$;
 3   DROP FUNCTION photogallery."NewUser_Collection"();
       photogallery          postgres    false    6            �            1255    18443    Photo_Tag_Check()    FUNCTION        CREATE FUNCTION photogallery."Photo_Tag_Check"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

new_photo photogallery.photo.photo_code%TYPE;
new_tag photogallery.tag.tag_name%TYPE;

BEGIN

SELECT T.tag_name INTO new_tag
FROM photogallery.TAG as T
WHERE NEW.tag_name = T.tag_name;

SELECT PH.Photo_Code INTO new_photo
FROM photogallery.photo as PH
WHERE NEW.photo_code = PH.photo_code;

IF
(new_photo IS NOT NULL AND new_tag IS NOT NULL)
THEN
INSERT INTO photogallery.photo_tag
VALUES (new_tag, new_photo);
END IF;
RETURN NULL;
END;$$;
 0   DROP FUNCTION photogallery."Photo_Tag_Check"();
       photogallery          postgres    false    6            �            1255    18444    Private_Photo()    FUNCTION       CREATE FUNCTION photogallery."Private_Photo"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

scope_test photogallery.photo.photo_code%TYPE;

BEGIN

SELECT PH.photo_code into scope_test
FROM photogallery.photo as PH JOIN photogallery.shared_photo as SP on SP.photo_code = PH.photo_code
WHERE PH.Scope = 'Private' and PH.Photo_Code = SP.Photo_Code;

IF scope_test IS NOT NULL
THEN
DELETE FROM photogallery.shared_photo
WHERE (photogallery.shared_photo.photo_code = scope_test);
END IF;
RETURN NULL;
END;




$$;
 .   DROP FUNCTION photogallery."Private_Photo"();
       photogallery          postgres    false    6            �            1255    18445    Public_Photo()    FUNCTION       CREATE FUNCTION photogallery."Public_Photo"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

scope_test photogallery.photo.photo_code%TYPE;

BEGIN

SELECT PH.photo_code into scope_test
FROM photogallery.photo as PH JOIN photogallery.shared_photo as SP on SP.photo_code = PH.photo_code
WHERE PH.Scope = 'Private' and PH.Photo_Code = SP.Photo_Code;

IF scope_test IS NOT NULL
THEN
UPDATE photogallery.photo
SET scope = 'Public'
WHERE (photogallery.photo.photo_code = scope_test);
END IF;
RETURN NULL;
END;$$;
 -   DROP FUNCTION photogallery."Public_Photo"();
       photogallery          postgres    false    6            �            1255    18446    User_Tag_Check()    FUNCTION     �  CREATE FUNCTION photogallery."User_Tag_Check"() RETURNS trigger
    LANGUAGE plpgsql
    AS $$DECLARE

new_photo photogallery.photo.photo_code%TYPE;
new_user photogallery.utente.nickname%TYPE;

BEGIN

SELECT U.Nickname INTO new_user
FROM photogallery.utente as U
WHERE NEW.nickname = U.Nickname
LIMIT 1;

SELECT PH.Photo_Code INTO new_photo
FROM photogallery.photo as PH
WHERE NEW.photo_code = PH.photo_code
LIMIT 1;

INSERT INTO photogallery.user_tag
VALUES(new_photo, new_user);
RETURN NULL;
END;$$;
 /   DROP FUNCTION photogallery."User_Tag_Check"();
       photogallery          postgres    false    6            �            1255    18447    elimina_foto(integer) 	   PROCEDURE     �   CREATE PROCEDURE photogallery.elimina_foto(IN ph_code integer)
    LANGUAGE sql
    AS $$UPDATE photogallery.PHOTO
SET SCOPE = 'Eliminated'
WHERE ph_code = PHOTO_CODE
$$;
 >   DROP PROCEDURE photogallery.elimina_foto(IN ph_code integer);
       photogallery          postgres    false    6            �            1255    18448    foto_stesso_luogo(text)    FUNCTION     K  CREATE FUNCTION photogallery.foto_stesso_luogo(luogo text) RETURNS TABLE(photo_code text, nickname text, location_name text, device text, photo_date date)
    LANGUAGE sql
    AS $$
  SELECT PH.photo_code, PH.nickname, PH.location_name, PH.device, PH.photo_date
  FROM photogallery.PHOTO AS PH
  WHERE PH.location_name = luogo
$$;
 :   DROP FUNCTION photogallery.foto_stesso_luogo(luogo text);
       photogallery          postgres    false    6            �            1255    18449    foto_stesso_soggetto(text)    FUNCTION     �  CREATE FUNCTION photogallery.foto_stesso_soggetto(soggetto text) RETURNS TABLE(photo_code text, nickname text, location_name text, device text, photo_date date)
    LANGUAGE sql
    AS $$
  SELECT PH.photo_code, PH.nickname, PH.location_name, PH.device, PH.photo_date
  FROM photogallery.PHOTO AS PH JOIN photogallery.photo_tag AS PT ON PH.photo_code = PT.photo_code
  WHERE PT.photo_code = PH.photo_code AND PT.tag_name = soggetto
$$;
 @   DROP FUNCTION photogallery.foto_stesso_soggetto(soggetto text);
       photogallery          postgres    false    6            �            1255    18450    galleriapersonale(text)    FUNCTION       CREATE FUNCTION photogallery.galleriapersonale(nick text) RETURNS TABLE(photo_code integer)
    LANGUAGE sql
    AS $$
SELECT PH.photo_code
FROM photogallery.photo as PH JOIN photogallery.utente as U ON PH.nickname = U.nickname
WHERE U.nickname = nick AND PH.scope <> 'Eliminated'$$;
 9   DROP FUNCTION photogallery.galleriapersonale(nick text);
       photogallery          postgres    false    6            �            1255    18451    galleriapersonalevideo(text)    FUNCTION     f  CREATE FUNCTION photogallery.galleriapersonalevideo(nick text) RETURNS TABLE(video_code integer, video_title text, video_lenght text, video_desc text)
    LANGUAGE sql
    AS $$
SELECT V.video_code, V.video_title, V.video_length, V.video_desc
FROM photogallery.video as V JOIN photogallery.utente as U ON V.nickname = U.nickname
WHERE V.nickname = nick 
$$;
 >   DROP FUNCTION photogallery.galleriapersonalevideo(nick text);
       photogallery          postgres    false    6            �            1255    18452    rendi_foto_privata(integer) 	   PROCEDURE     �   CREATE PROCEDURE photogallery.rendi_foto_privata(IN ph_code integer)
    LANGUAGE sql
    AS $$UPDATE photogallery.PHOTO
SET SCOPE = 'Private'
WHERE ph_code = PHOTO_CODE
$$;
 D   DROP PROCEDURE photogallery.rendi_foto_privata(IN ph_code integer);
       photogallery          postgres    false    6            �            1255    18453    rendi_foto_pubblica(integer) 	   PROCEDURE     �   CREATE PROCEDURE photogallery.rendi_foto_pubblica(IN ph_code integer)
    LANGUAGE sql
    AS $$UPDATE photogallery.PHOTO
SET SCOPE = 'Public'
WHERE ph_code = PHOTO_CODE$$;
 E   DROP PROCEDURE photogallery.rendi_foto_pubblica(IN ph_code integer);
       photogallery          postgres    false    6            �            1255    18454    testdevice()    FUNCTION     �   CREATE FUNCTION photogallery.testdevice() RETURNS TABLE(name text)
    LANGUAGE sql
    AS $$
  SELECT PH.DEVICE
  FROM photogallery.PHOTO AS PH
$$;
 )   DROP FUNCTION photogallery.testdevice();
       photogallery          postgres    false    6            �            1255    18455    top_3_luoghi()    FUNCTION     �   CREATE FUNCTION photogallery.top_3_luoghi() RETURNS TABLE(location_name text, photo_count integer)
    LANGUAGE sql
    AS $$
SELECT location_name, photo_count
FROM photogallery.location
ORDER BY photo_count DESC
LIMIT 3;
$$;
 +   DROP FUNCTION photogallery.top_3_luoghi();
       photogallery          postgres    false    6                       1255    18456    video_foto(integer)    FUNCTION     N  CREATE FUNCTION photogallery.video_foto(video_cod integer) RETURNS TABLE(video_title text, video_code text, photo_code text)
    LANGUAGE sql
    AS $$
	SELECT V.video_title, I.video_code, I.photo_code
	FROM photogallery.is_in_video as I JOIN photogallery.video as V on I.video_code = V.video_code
	WHERE V.video_code = video_cod
$$;
 :   DROP FUNCTION photogallery.video_foto(video_cod integer);
       photogallery          postgres    false    6            �            1259    18457    is_in_video    TABLE     l   CREATE TABLE photogallery.is_in_video (
    video_code integer NOT NULL,
    photo_code integer NOT NULL
);
 %   DROP TABLE photogallery.is_in_video;
       photogallery         heap    postgres    false    6            �            1259    18460    is_in_video_photo_code_seq    SEQUENCE     �   CREATE SEQUENCE photogallery.is_in_video_photo_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE photogallery.is_in_video_photo_code_seq;
       photogallery          postgres    false    6    216            h           0    0    is_in_video_photo_code_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE photogallery.is_in_video_photo_code_seq OWNED BY photogallery.is_in_video.photo_code;
          photogallery          postgres    false    217            �            1259    18461    is_in_video_video_code_seq    SEQUENCE     �   CREATE SEQUENCE photogallery.is_in_video_video_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE photogallery.is_in_video_video_code_seq;
       photogallery          postgres    false    216    6            i           0    0    is_in_video_video_code_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE photogallery.is_in_video_video_code_seq OWNED BY photogallery.is_in_video.video_code;
          photogallery          postgres    false    218            �            1259    18462    location    TABLE     �   CREATE TABLE photogallery.location (
    location_name character varying(64) NOT NULL,
    x_coordinates double precision,
    y_coordinates double precision,
    photo_count integer
);
 "   DROP TABLE photogallery.location;
       photogallery         heap    postgres    false    6            �            1259    18465    partecipating_users    TABLE     �   CREATE TABLE photogallery.partecipating_users (
    join_date date NOT NULL,
    nickname character varying(32) NOT NULL,
    collection_name character varying(32) NOT NULL
);
 -   DROP TABLE photogallery.partecipating_users;
       photogallery         heap    postgres    false    6            �            1259    18468    photo    TABLE     �  CREATE TABLE photogallery.photo (
    photo_code integer NOT NULL,
    scope character varying(16) DEFAULT 'Private'::character varying NOT NULL,
    nickname character varying(32) NOT NULL,
    location_name character varying(32),
    device character varying(32) NOT NULL,
    photo_date date NOT NULL,
    path character varying(128),
    x_coordinates double precision,
    y_coordinates double precision
);
    DROP TABLE photogallery.photo;
       photogallery         heap    postgres    false    6            �            1259    18472    photo_photo_code_seq    SEQUENCE     �   CREATE SEQUENCE photogallery.photo_photo_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE photogallery.photo_photo_code_seq;
       photogallery          postgres    false    6    221            j           0    0    photo_photo_code_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE photogallery.photo_photo_code_seq OWNED BY photogallery.photo.photo_code;
          photogallery          postgres    false    222            �            1259    18473 	   photo_tag    TABLE     v   CREATE TABLE photogallery.photo_tag (
    tag_name character varying(32) NOT NULL,
    photo_code integer NOT NULL
);
 #   DROP TABLE photogallery.photo_tag;
       photogallery         heap    postgres    false    6            �            1259    18476    public_collection    TABLE     d   CREATE TABLE photogallery.public_collection (
    collection_name character varying(32) NOT NULL
);
 +   DROP TABLE photogallery.public_collection;
       photogallery         heap    postgres    false    6            �            1259    18479    shared_photo    TABLE     �   CREATE TABLE photogallery.shared_photo (
    collection_name character varying(32) NOT NULL,
    photo_code integer NOT NULL
);
 &   DROP TABLE photogallery.shared_photo;
       photogallery         heap    postgres    false    6            �            1259    18482    tag    TABLE     O   CREATE TABLE photogallery.tag (
    tag_name character varying(32) NOT NULL
);
    DROP TABLE photogallery.tag;
       photogallery         heap    postgres    false    6            �            1259    18485    user_tag    TABLE     u   CREATE TABLE photogallery.user_tag (
    photo_code integer NOT NULL,
    nickname character varying(32) NOT NULL
);
 "   DROP TABLE photogallery.user_tag;
       photogallery         heap    postgres    false    6            �            1259    18488    utente    TABLE     �   CREATE TABLE photogallery.utente (
    nickname character varying(32) NOT NULL,
    birthdate date,
    gender character varying(1),
    name photogallery.fullname,
    surname photogallery.fullname,
    passwordutente character varying(32)
);
     DROP TABLE photogallery.utente;
       photogallery         heap    postgres    false    875    875    6            �            1259    18493    video    TABLE     �   CREATE TABLE photogallery.video (
    video_code integer NOT NULL,
    video_desc character varying(128),
    video_title character varying(32) NOT NULL,
    nickname character varying(32) NOT NULL
);
    DROP TABLE photogallery.video;
       photogallery         heap    postgres    false    6            �            1259    18496    video_video_code_seq    SEQUENCE     �   CREATE SEQUENCE photogallery.video_video_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE photogallery.video_video_code_seq;
       photogallery          postgres    false    6    229            k           0    0    video_video_code_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE photogallery.video_video_code_seq OWNED BY photogallery.video.video_code;
          photogallery          postgres    false    230            �           2604    18497    is_in_video video_code    DEFAULT     �   ALTER TABLE ONLY photogallery.is_in_video ALTER COLUMN video_code SET DEFAULT nextval('photogallery.is_in_video_video_code_seq'::regclass);
 K   ALTER TABLE photogallery.is_in_video ALTER COLUMN video_code DROP DEFAULT;
       photogallery          postgres    false    218    216            �           2604    18498    is_in_video photo_code    DEFAULT     �   ALTER TABLE ONLY photogallery.is_in_video ALTER COLUMN photo_code SET DEFAULT nextval('photogallery.is_in_video_photo_code_seq'::regclass);
 K   ALTER TABLE photogallery.is_in_video ALTER COLUMN photo_code DROP DEFAULT;
       photogallery          postgres    false    217    216            �           2604    18499    photo photo_code    DEFAULT     �   ALTER TABLE ONLY photogallery.photo ALTER COLUMN photo_code SET DEFAULT nextval('photogallery.photo_photo_code_seq'::regclass);
 E   ALTER TABLE photogallery.photo ALTER COLUMN photo_code DROP DEFAULT;
       photogallery          postgres    false    222    221            �           2604    18500    video video_code    DEFAULT     �   ALTER TABLE ONLY photogallery.video ALTER COLUMN video_code SET DEFAULT nextval('photogallery.video_video_code_seq'::regclass);
 E   ALTER TABLE photogallery.video ALTER COLUMN video_code DROP DEFAULT;
       photogallery          postgres    false    230    229            S          0    18457    is_in_video 
   TABLE DATA           C   COPY photogallery.is_in_video (video_code, photo_code) FROM stdin;
    photogallery          postgres    false    216   v�       V          0    18462    location 
   TABLE DATA           b   COPY photogallery.location (location_name, x_coordinates, y_coordinates, photo_count) FROM stdin;
    photogallery          postgres    false    219   ��       W          0    18465    partecipating_users 
   TABLE DATA           Y   COPY photogallery.partecipating_users (join_date, nickname, collection_name) FROM stdin;
    photogallery          postgres    false    220   �       X          0    18468    photo 
   TABLE DATA           �   COPY photogallery.photo (photo_code, scope, nickname, location_name, device, photo_date, path, x_coordinates, y_coordinates) FROM stdin;
    photogallery          postgres    false    221   ��       Z          0    18473 	   photo_tag 
   TABLE DATA           ?   COPY photogallery.photo_tag (tag_name, photo_code) FROM stdin;
    photogallery          postgres    false    223   �       [          0    18476    public_collection 
   TABLE DATA           B   COPY photogallery.public_collection (collection_name) FROM stdin;
    photogallery          postgres    false    224   z�       \          0    18479    shared_photo 
   TABLE DATA           I   COPY photogallery.shared_photo (collection_name, photo_code) FROM stdin;
    photogallery          postgres    false    225   ��       ]          0    18482    tag 
   TABLE DATA           -   COPY photogallery.tag (tag_name) FROM stdin;
    photogallery          postgres    false    226   �       ^          0    18485    user_tag 
   TABLE DATA           >   COPY photogallery.user_tag (photo_code, nickname) FROM stdin;
    photogallery          postgres    false    227   V�       _          0    18488    utente 
   TABLE DATA           b   COPY photogallery.utente (nickname, birthdate, gender, name, surname, passwordutente) FROM stdin;
    photogallery          postgres    false    228   ��       `          0    18493    video 
   TABLE DATA           T   COPY photogallery.video (video_code, video_desc, video_title, nickname) FROM stdin;
    photogallery          postgres    false    229   ��       l           0    0    is_in_video_photo_code_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('photogallery.is_in_video_photo_code_seq', 1, false);
          photogallery          postgres    false    217            m           0    0    is_in_video_video_code_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('photogallery.is_in_video_video_code_seq', 1, false);
          photogallery          postgres    false    218            n           0    0    photo_photo_code_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('photogallery.photo_photo_code_seq', 41, true);
          photogallery          postgres    false    222            o           0    0    video_video_code_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('photogallery.video_video_code_seq', 1, true);
          photogallery          postgres    false    230            �           2606    18501    utente Check_Gender    CHECK CONSTRAINT     �   ALTER TABLE photogallery.utente
    ADD CONSTRAINT "Check_Gender" CHECK ((((gender)::text = 'M'::text) OR ((gender)::text = 'F'::text))) NOT VALID;
 @   ALTER TABLE photogallery.utente DROP CONSTRAINT "Check_Gender";
       photogallery          postgres    false    228    228            �           2606    18503    is_in_video is_in_video_pkey 
   CONSTRAINT     t   ALTER TABLE ONLY photogallery.is_in_video
    ADD CONSTRAINT is_in_video_pkey PRIMARY KEY (video_code, photo_code);
 L   ALTER TABLE ONLY photogallery.is_in_video DROP CONSTRAINT is_in_video_pkey;
       photogallery            postgres    false    216    216            �           2606    18505    location location_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY photogallery.location
    ADD CONSTRAINT location_pkey PRIMARY KEY (location_name);
 F   ALTER TABLE ONLY photogallery.location DROP CONSTRAINT location_pkey;
       photogallery            postgres    false    219            �           2606    18507 ,   partecipating_users partecipating_users_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY photogallery.partecipating_users
    ADD CONSTRAINT partecipating_users_pkey PRIMARY KEY (nickname, collection_name);
 \   ALTER TABLE ONLY photogallery.partecipating_users DROP CONSTRAINT partecipating_users_pkey;
       photogallery            postgres    false    220    220            �           2606    18509    photo photo_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY photogallery.photo
    ADD CONSTRAINT photo_pkey PRIMARY KEY (photo_code);
 @   ALTER TABLE ONLY photogallery.photo DROP CONSTRAINT photo_pkey;
       photogallery            postgres    false    221            �           2606    18511    photo_tag photo_tag_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY photogallery.photo_tag
    ADD CONSTRAINT photo_tag_pkey PRIMARY KEY (tag_name, photo_code);
 H   ALTER TABLE ONLY photogallery.photo_tag DROP CONSTRAINT photo_tag_pkey;
       photogallery            postgres    false    223    223            �           2606    18513 (   public_collection public_collection_pkey 
   CONSTRAINT     y   ALTER TABLE ONLY photogallery.public_collection
    ADD CONSTRAINT public_collection_pkey PRIMARY KEY (collection_name);
 X   ALTER TABLE ONLY photogallery.public_collection DROP CONSTRAINT public_collection_pkey;
       photogallery            postgres    false    224            �           2606    18515    shared_photo shared_photo_pkey 
   CONSTRAINT     {   ALTER TABLE ONLY photogallery.shared_photo
    ADD CONSTRAINT shared_photo_pkey PRIMARY KEY (photo_code, collection_name);
 N   ALTER TABLE ONLY photogallery.shared_photo DROP CONSTRAINT shared_photo_pkey;
       photogallery            postgres    false    225    225            �           2606    18517    tag tag_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY photogallery.tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (tag_name);
 <   ALTER TABLE ONLY photogallery.tag DROP CONSTRAINT tag_pkey;
       photogallery            postgres    false    226            �           2606    18519    utente user_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY photogallery.utente
    ADD CONSTRAINT user_pkey PRIMARY KEY (nickname);
 @   ALTER TABLE ONLY photogallery.utente DROP CONSTRAINT user_pkey;
       photogallery            postgres    false    228            �           2606    18521    user_tag user_tag_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY photogallery.user_tag
    ADD CONSTRAINT user_tag_pkey PRIMARY KEY (photo_code, nickname);
 F   ALTER TABLE ONLY photogallery.user_tag DROP CONSTRAINT user_tag_pkey;
       photogallery            postgres    false    227    227            �           2606    18523    video video_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY photogallery.video
    ADD CONSTRAINT video_pkey PRIMARY KEY (video_code);
 @   ALTER TABLE ONLY photogallery.video DROP CONSTRAINT video_pkey;
       photogallery            postgres    false    229            �           2620    18524     shared_photo Add_User_Collection    TRIGGER     �   CREATE TRIGGER "Add_User_Collection" AFTER INSERT ON photogallery.shared_photo FOR EACH ROW EXECUTE FUNCTION photogallery."NewUser_Collection"();
 A   DROP TRIGGER "Add_User_Collection" ON photogallery.shared_photo;
       photogallery          postgres    false    234    225            �           2620    18921    photo Delete_Photo    TRIGGER        CREATE TRIGGER "Delete_Photo" BEFORE DELETE ON photogallery.photo FOR EACH ROW EXECUTE FUNCTION photogallery."Delete_Photo"();
 3   DROP TRIGGER "Delete_Photo" ON photogallery.photo;
       photogallery          postgres    false    261    221            �           2620    18526    utente Delete_User    TRIGGER     }   CREATE TRIGGER "Delete_User" AFTER DELETE ON photogallery.utente FOR EACH ROW EXECUTE FUNCTION photogallery."Delete_User"();
 3   DROP TRIGGER "Delete_User" ON photogallery.utente;
       photogallery          postgres    false    260    228            �           2620    18924    video Delete_Video    TRIGGER        CREATE TRIGGER "Delete_Video" BEFORE DELETE ON photogallery.video FOR EACH ROW EXECUTE FUNCTION photogallery."Delete_Video"();
 3   DROP TRIGGER "Delete_Video" ON photogallery.video;
       photogallery          postgres    false    231    229            �           2620    18527    photo Private_Photo    TRIGGER     �   CREATE TRIGGER "Private_Photo" AFTER INSERT OR UPDATE ON photogallery.photo FOR EACH ROW EXECUTE FUNCTION photogallery."Private_Photo"();
 4   DROP TRIGGER "Private_Photo" ON photogallery.photo;
       photogallery          postgres    false    221    236            �           2620    18528    shared_photo Public_Photo    TRIGGER     �   CREATE TRIGGER "Public_Photo" AFTER INSERT ON photogallery.shared_photo FOR EACH ROW EXECUTE FUNCTION photogallery."Public_Photo"();
 :   DROP TRIGGER "Public_Photo" ON photogallery.shared_photo;
       photogallery          postgres    false    225    237            �           2620    18529    user_tag User_Tag_Check    TRIGGER     �   CREATE TRIGGER "User_Tag_Check" BEFORE INSERT OR UPDATE ON photogallery.user_tag FOR EACH ROW EXECUTE FUNCTION photogallery."User_Tag_Check"();

ALTER TABLE photogallery.user_tag DISABLE TRIGGER "User_Tag_Check";
 8   DROP TRIGGER "User_Tag_Check" ON photogallery.user_tag;
       photogallery          postgres    false    227    238            �           2620    18530    photo photo_count_insert    TRIGGER     �   CREATE TRIGGER photo_count_insert AFTER INSERT ON photogallery.photo FOR EACH ROW EXECUTE FUNCTION photogallery."Location_Count"();
 7   DROP TRIGGER photo_count_insert ON photogallery.photo;
       photogallery          postgres    false    232    221            �           2620    18531    photo photo_count_subtract    TRIGGER     �   CREATE TRIGGER photo_count_subtract AFTER DELETE ON photogallery.photo FOR EACH ROW EXECUTE FUNCTION photogallery."Location_Count_Subtract"();
 9   DROP TRIGGER photo_count_subtract ON photogallery.photo;
       photogallery          postgres    false    233    221            �           2606    18532 '   is_in_video is_in_video_photo_code_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.is_in_video
    ADD CONSTRAINT is_in_video_photo_code_fkey FOREIGN KEY (photo_code) REFERENCES photogallery.photo(photo_code);
 W   ALTER TABLE ONLY photogallery.is_in_video DROP CONSTRAINT is_in_video_photo_code_fkey;
       photogallery          postgres    false    221    4769    216            �           2606    18537 '   is_in_video is_in_video_video_code_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.is_in_video
    ADD CONSTRAINT is_in_video_video_code_fkey FOREIGN KEY (video_code) REFERENCES photogallery.video(video_code);
 W   ALTER TABLE ONLY photogallery.is_in_video DROP CONSTRAINT is_in_video_video_code_fkey;
       photogallery          postgres    false    229    4783    216            �           2606    18542 <   partecipating_users partecipating_users_collection_name_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.partecipating_users
    ADD CONSTRAINT partecipating_users_collection_name_fkey FOREIGN KEY (collection_name) REFERENCES photogallery.public_collection(collection_name);
 l   ALTER TABLE ONLY photogallery.partecipating_users DROP CONSTRAINT partecipating_users_collection_name_fkey;
       photogallery          postgres    false    224    220    4773            �           2606    18547 5   partecipating_users partecipating_users_nickname_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.partecipating_users
    ADD CONSTRAINT partecipating_users_nickname_fkey FOREIGN KEY (nickname) REFERENCES photogallery.utente(nickname);
 e   ALTER TABLE ONLY photogallery.partecipating_users DROP CONSTRAINT partecipating_users_nickname_fkey;
       photogallery          postgres    false    228    220    4781            �           2606    18552    photo photo_location_name_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.photo
    ADD CONSTRAINT photo_location_name_fkey FOREIGN KEY (location_name) REFERENCES photogallery.location(location_name);
 N   ALTER TABLE ONLY photogallery.photo DROP CONSTRAINT photo_location_name_fkey;
       photogallery          postgres    false    221    4765    219            �           2606    18557    photo photo_nickname_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.photo
    ADD CONSTRAINT photo_nickname_fkey FOREIGN KEY (nickname) REFERENCES photogallery.utente(nickname);
 I   ALTER TABLE ONLY photogallery.photo DROP CONSTRAINT photo_nickname_fkey;
       photogallery          postgres    false    228    4781    221            �           2606    18562 #   photo_tag photo_tag_photo_code_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.photo_tag
    ADD CONSTRAINT photo_tag_photo_code_fkey FOREIGN KEY (photo_code) REFERENCES photogallery.photo(photo_code);
 S   ALTER TABLE ONLY photogallery.photo_tag DROP CONSTRAINT photo_tag_photo_code_fkey;
       photogallery          postgres    false    221    4769    223            �           2606    18567 !   photo_tag photo_tag_tag_name_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.photo_tag
    ADD CONSTRAINT photo_tag_tag_name_fkey FOREIGN KEY (tag_name) REFERENCES photogallery.tag(tag_name);
 Q   ALTER TABLE ONLY photogallery.photo_tag DROP CONSTRAINT photo_tag_tag_name_fkey;
       photogallery          postgres    false    226    223    4777            �           2606    18572 .   shared_photo shared_photo_collection_name_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.shared_photo
    ADD CONSTRAINT shared_photo_collection_name_fkey FOREIGN KEY (collection_name) REFERENCES photogallery.public_collection(collection_name);
 ^   ALTER TABLE ONLY photogallery.shared_photo DROP CONSTRAINT shared_photo_collection_name_fkey;
       photogallery          postgres    false    224    225    4773            �           2606    18577    user_tag user_tag_nickname_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.user_tag
    ADD CONSTRAINT user_tag_nickname_fkey FOREIGN KEY (nickname) REFERENCES photogallery.utente(nickname);
 O   ALTER TABLE ONLY photogallery.user_tag DROP CONSTRAINT user_tag_nickname_fkey;
       photogallery          postgres    false    4781    228    227            �           2606    18582    video video_nickname_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY photogallery.video
    ADD CONSTRAINT video_nickname_fkey FOREIGN KEY (nickname) REFERENCES photogallery.utente(nickname);
 I   ALTER TABLE ONLY photogallery.video DROP CONSTRAINT video_nickname_fkey;
       photogallery          postgres    false    228    4781    229            S      x�3�4�2�4�2�4f\1z\\\ #��      V   b   x��1�0�z�1�\��|/�E�4.RDrr���Ô����D�t������J�RA���~�w@��B�g��p|��=�;�-�.��^4�7�B      W   ~   x�3202�50�50�tK,.	��K�t���I����KU0�2)04�52�t�(�)64Ð7�q�$&��w*-)I-J˩��ĩ& �� �3,191�*Q!*��4/Yޭ(1/9���%(v��qqq (C      X   h  x����n�0���S��(pJ�qmRN�K ��h�--*���}��[��N� �.����R4
�Aw�g:S%�Zеn��hh�C�P�W�^# ��Bʲeh�O�-�L��фƳ�D>�m7��|w����$b0c�0�`sW|�����T���Η?���eR�Ծ�X/ھ�'�����'�!P�t�QN��,ǳY)s>,Ih.��FbEKș�$��w����]Y�k���S'g_�	BN7��0$��L�d\�����˥��1����)RȐJ�t����Z�S��:/��dDXrAP)�J��$�"9�ʘ���r�-�I�u&��o��`;zV=���Ѣo�Ot�L��Ζ:)cmb�����Ǵ�Op�x���`��p�{�4:^���hfN�"�a�?�դ���/�����	\�_׏w.;I>ЏX�ԩAץ��|aj��_2W�2�n�����m�+����->7!�`[��\# �X�T��g[��6������E�Mv��t��_'z�1h���M4/�E�I��}=ƎO�H;;�W��\`�Z1\�q]P��8�l��4�c��z�D��č�:��]����^��Ҧf�W�vAW�|.{���$�>      Z   Q   x��)�O�WH�T��+I-J-.N�46���"j�����	fe�%���s[p����B�0�\�e�y 18���&\1z\\\ (�      [   )   x�s���I����KU0�
KLN̫JT�J-*������� �{
�      \   >   x�KLN̫JT�J-*���46�
C1�r���I����KU0���r�U���-��\1z\\\ �|)K      ]   E   x�N�I�L�
HL-NLO���r-K�+���)�O�WH�T��+I-J-.N��OOO-Je�%�1z\\\ ��5      ^   6   x�32�tK,.	��K�2�@���8݊���L8�JKJR��r*--�b���� ��      _   �   x�U��n�0D����v��H��)�*�*U\ܰ��L�6N�����ҜFo�f́&���Q�cJ,��gT���;~��5OV��(⁪z��	-��1IA��.�e���m���҇�o��^�߹2JB��QYq}3Cf�go���'�����B���9.;�y�|������
Luy����0��x��K|t��,��~����sQ�      `   e   x�3�KLN̫JTH��S���WH+J,I���WHTpO��/K���t+J�K�70�2�k��SH�,��JLN��)ITHjM�����_���Q�Slh����� �o#(     