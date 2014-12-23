package sendrovitz.nytimes;

import java.net.URI;

public class Docs {
	private URI web_url;
	private String lead_paragraph;
	private Headline headline;

	public URI getWeb_url() {
		return web_url;
	}

	public String getLead_paragraph() {
		return lead_paragraph;
	}

	public Headline getHeadline() {
		return headline;
	}
}
