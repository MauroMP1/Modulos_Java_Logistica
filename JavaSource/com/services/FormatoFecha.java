package com.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class FormatoFecha extends MaskFormatter
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Se construye con el patr�n deseado */
    public FormatoFecha() throws ParseException
    {
        // Las # son cifras y representa "dd/mm/yy hh:mm:ss"
        super ("##/##/##");
    }

    /** Una clase adecuada para convertir Date a String y viceversa de forma c�moda. Puedes ver c�mo se hace el patr�n "dd/MM/yy kk:mm:ss" en la API.
        El patr�n que pongamos aqu� debe cuadrar correctamente con la m�scara que hemos puesto en el constructor */
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

    /** Convierte el texto del editor en un Date */
    public Object stringToValue(String text) throws ParseException
    {
        return formato.parseObject(text);
    }

    /** Redibe un Date o null y debe convertirlo a texto que cumpla el patr�n indicado anteriormente */
    public String valueToString(Object value) throws ParseException
    {
        if (value instanceof Date)
            return formato.format((Date)value);
        return formato.format(new Date());
    }
}
