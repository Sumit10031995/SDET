package api.request;

import okhttp3.*;

import java.io.IOException;

import dev.failsafe.RetryPolicy;

public class CurlCaptureExample {

	private static String constructCurlCommand(Request request) {
		StringBuilder curlCommand = new StringBuilder("curl");
		curlCommand.append(" -X ").append(request.method());
		for (String name : request.headers().names()) {
			curlCommand.append(" -H '").append(name).append(": ").append(request.header(name)).append("'");
		}

		if (request.body() instanceof FormBody) {
			FormBody body = (FormBody) request.body();
			for (int i = 0; i < body.size(); i++) {
				curlCommand.append(" -d '").append(body.name(i)).append("=").append(body.value(i)).append("'");
			}
		}

		curlCommand.append(" ").append(request.url());

		return curlCommand.toString();
	}
}
