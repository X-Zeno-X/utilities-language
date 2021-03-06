package zeno.util.lang.time;

import zeno.util.lang.format.Format;
import zeno.util.lang.format.Formattable;
import zeno.util.lang.format.types.FMTDate;
import zeno.util.lang.time.iso.date.Month;
import zeno.util.lang.time.iso.date.WeekDay;
import zeno.util.lang.util.ISO;
import zeno.util.tools.Longs;

/**
 * The {@code Date} interface defines a generic date-like object.
 *
 * @author Zeno
 * @since 26 Jul 2020
 * @version 1.0
 * 
 * 
 * @see Formattable
 * @see Comparable
 */
public interface Date extends Comparable<Date>, Formattable<Date>
{	
	/**
	 * Defines a {@code Format} for a long date string.
	 */
	public static FMTDate LONG_DATE = new FMTDate("%WEEKDAY%, %D% %MONTH% %Y%");
	
	/**
	 * Defines a {@code Format} for a short date string.
	 */
	public static FMTDate SHORT_DATE = new FMTDate("%dd%-%mm%-%yyyy%");
	
	
	/**
	 * Parses an {@code ISO Format} from the {@code Date}.
	 * 
	 * @param fmt  an iso format
	 * @return  a parsed string
	 * 
	 * 
	 * @see String
	 * @see ISO
	 */
	public default String toString(ISO.Format fmt)
	{
		switch(fmt)
		{
		case SHORT:
			return format(SHORT_DATE);
		case LONG:
			return format(LONG_DATE);
		default:
			return null;
		}
	}
		
	/**
	 * Checks if the date exists before another {@code Date}.
	 * 
	 * @param d  a date to compare with
	 * @return  {@code true} if this date is earlier
	 */
	public default boolean before(Date d)
	{
		return compareTo(d) < 0;
	}
	
	/**
	 * Checks if the date exists after another {@code Date}.
	 * 
	 * @param d  a date to compare with
	 * @return  {@code true} if this date is later
	 */
	public default boolean after(Date d)
	{
		return compareTo(d) > 0;
	}
		
			
	@Override
	public default Format<Date> Formatter(String fmt)
	{
		return new FMTDate(fmt);
	}
	
	@Override
	public default int compareTo(Date d)
	{
		if(Year() < d.Year()) return -1;
		if(d.Year() < Year()) return +1;
		return (int) Longs.sign(DayOfYear() - d.DayOfYear());
	}
	
	
	/**
	 * Returns the month in the {@code Date}.
	 * 
	 * @return  a month
	 * 
	 * 
	 * @see Month
	 */
	public abstract Month Month();
	
	/**
	 * Returns the day of the week in the {@code Date}.
	 * 
	 * @return  a week day
	 * 
	 * 
	 * @see WeekDay
	 */
	public abstract WeekDay DayOfWeek();

	/**
	 * Returns the day of the month in the {@code Date}.
	 * 
	 * @return  a day of the month
	 */
	public abstract long DayOfMonth();
	
	/**
	 * Returns the day of the year in the {@code Date}.
	 * 
	 * @return  a day of the year
	 */
	public abstract long DayOfYear();
	
	/**
	 * Returns the year in the {@code Date}.
	 * 
	 * @return  a year
	 */
	public abstract long Year();
}