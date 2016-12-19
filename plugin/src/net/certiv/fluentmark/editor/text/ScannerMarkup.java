package net.certiv.fluentmark.editor.text;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.WhitespaceRule;

import net.certiv.fluentmark.preferences.Prefs;

public class ScannerMarkup extends AbstractBufferedRuleBasedScanner {

	private String[] tokenProperties;

	public ScannerMarkup() {
		super();
		initialize();
	}

	@Override
	protected String[] getTokenProperties() {
		if (tokenProperties == null) {
			tokenProperties = new String[] { //
					Prefs.EDITOR_HEADER_COLOR, //
					Prefs.EDITOR_LIST_COLOR, //
					Prefs.EDITOR_LINK_COLOR, //
					Prefs.EDITOR_HRULE_COLOR, //
					Prefs.EDITOR_BOLD_COLOR, //
					Prefs.EDITOR_ITALIC_COLOR, //
					Prefs.EDITOR_STRIKEOUT_COLOR, //
					Prefs.EDITOR_CODE_COLOR, //
			};
		}
		return tokenProperties;
	}

	@Override
	protected List<IRule> createRules() {
		IToken header = getToken(Prefs.EDITOR_HEADER_COLOR);
		IToken link = getToken(Prefs.EDITOR_LINK_COLOR);
		IToken list = getToken(Prefs.EDITOR_LIST_COLOR);
		IToken rule = getToken(Prefs.EDITOR_HRULE_COLOR);
		IToken bold = getToken(Prefs.EDITOR_BOLD_COLOR);
		IToken italic = getToken(Prefs.EDITOR_ITALIC_COLOR);
		IToken strikeout = getToken(Prefs.EDITOR_STRIKEOUT_COLOR);
		IToken code = getToken(Prefs.EDITOR_CODE_COLOR);

		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new HeaderUnderlineRule(header));
		rules.add(new FirstColumnRule("#", header));
		rules.add(new FirstColumnRule("---", rule));
		rules.add(new FirstColumnRule("***", rule));
		rules.add(new FirstColumnRule("___", rule));
		rules.add(new LinkRule(link));
		rules.add(new ListRule(list));
		rules.add(new EmphasisRule("__", bold));
		rules.add(new EmphasisRule("**", bold));
		rules.add(new EmphasisRule("_", italic));
		rules.add(new EmphasisRule("*", italic));
		rules.add(new EmphasisRule("~~", strikeout));
		rules.add(new EmphasisRule("``", code));
		rules.add(new EmphasisRule("`", code));
		rules.add(new WhitespaceRule(new WhitespaceDetector()));
		return rules;
	}
}
