DROP FUNCTION IF EXISTS t_say_hello;
CREATE FUNCTION t_say_hello() RETURNS text as '
    BEGIN
        return ''Hello World'';
    END;
' language plpgsql;