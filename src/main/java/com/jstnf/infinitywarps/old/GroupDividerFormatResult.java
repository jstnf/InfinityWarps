package com.jstnf.infinitywarps.old;

/**
 * InfinityWarps by jstnf
 * GroupDividerFormatResult
 * Encapsulation of objects returned by IWUtils.groupDividerFormat(String, String)
 *
 * @author jstnf / pokeball92870
 */
public class GroupDividerFormatResult
{
	public final boolean result;
	public final String groupName, warpName;

	public GroupDividerFormatResult(boolean result, String groupName, String warpName)
	{
		this.result = result;
		this.groupName = groupName;
		this.warpName = warpName;
	}
}