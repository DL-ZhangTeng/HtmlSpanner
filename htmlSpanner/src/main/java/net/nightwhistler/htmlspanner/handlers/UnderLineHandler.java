/*
 * Copyright (C) 2011 Alex Kuiper <http://www.nightwhistler.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.nightwhistler.htmlspanner.handlers;

import net.nightwhistler.htmlspanner.SpanStack;
import net.nightwhistler.htmlspanner.TagNodeHandler;

import org.htmlcleaner.TagNode;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.UnderlineSpan;
import android.util.Log;

/**
 * display the underline style
 * 
 * @author YH
 * 
 */
public class UnderLineHandler extends TagNodeHandler {

	
	@Override
	public void handleTagNode(TagNode node, SpannableStringBuilder builder,
			int start, int end, SpanStack spanStack) {
		
		final char space = (char)32; //space
		final char nul = (char)0;  //null
		final char ndsp = (char)160; //&ndsp
		final String underLine = "_";
		
		boolean isReplace = true;
		
		for(int i = start; i < end ; i ++) {
			if(!(builder.charAt(i) == ndsp || builder.charAt(i) == nul || builder.charAt(i) == space)){
				isReplace = false;
				break;
			}
		}
		
		if(isReplace) {
			for(int i = start; i < end; i++) {
				if(builder.charAt(i) == ndsp || builder.charAt(i) == nul || builder.charAt(i) == space) {
					builder.replace(i, i+1, underLine.subSequence(0, 1));
				}
			}
			
		}else {
			builder.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		
	}
}