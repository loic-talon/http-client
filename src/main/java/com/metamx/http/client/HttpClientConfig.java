/*
 * Copyright 2011 - 2015 Metamarkets Group Inc.
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

package com.metamx.http.client;

import org.joda.time.Duration;

import javax.net.ssl.SSLContext;

/**
 */
public class HttpClientConfig
{
  public static Builder builder()
  {
    return new Builder();
  }

  private final int numConnections;
  private final SSLContext sslContext;
  private final Duration readTimeout;
  private final Duration sslHandshakeTimeout;

  @Deprecated
  public HttpClientConfig(
      int numConnections,
      SSLContext sslContext
  )
  {
    this(numConnections, sslContext, Duration.ZERO, null);
  }

  public HttpClientConfig(
      int numConnections,
      SSLContext sslContext,
      Duration readTimeout
  )
  {
    this(numConnections, sslContext, readTimeout, null);
  }

  public HttpClientConfig(
      int numConnections,
      SSLContext sslContext,
      Duration readTimeout,
      Duration sslHandshakeTimeout
  )
  {
    this.numConnections = numConnections;
    this.sslContext = sslContext;
    this.readTimeout = readTimeout;
    this.sslHandshakeTimeout = sslHandshakeTimeout;
  }

  public int getNumConnections()
  {
    return numConnections;
  }

  public SSLContext getSslContext()
  {
    return sslContext;
  }

  public Duration getReadTimeout()
  {
    return readTimeout;
  }

  public Duration getSslHandshakeTimeout()
  {
    return sslHandshakeTimeout;
  }

  public static class Builder
  {
    private int numConnections = 1;
    private SSLContext sslContext = null;
    private Duration readTimeout = null;
    private Duration sslHandshakeTimeout = null;

    private Builder(){}

    public Builder withNumConnections(int numConnections)
    {
      this.numConnections = numConnections;
      return this;
    }

    public Builder withSslContext(SSLContext sslContext)
    {
      this.sslContext = sslContext;
      return this;
    }

    public Builder withSslContext(String keyStorePath, String keyStorePassword)
    {
      this.sslContext = HttpClientInit.sslContextWithTrustedKeyStore(keyStorePath, keyStorePassword);
      return this;
    }

    public Builder withReadTimeout(Duration readTimeout) {
      this.readTimeout = readTimeout;
      return this;
    }

    public Builder withSslHandshakeTimeout(Duration sslHandshakeTimeout) {
      this.sslHandshakeTimeout = sslHandshakeTimeout;
      return this;
    }

    public HttpClientConfig build()
    {
      return new HttpClientConfig(numConnections, sslContext, readTimeout, sslHandshakeTimeout);
    }
  }
}
