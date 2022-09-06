import { css } from '@emotion/react';
import type { NextPage } from 'next';
import Head from 'next/head';

const Home: NextPage = () => (
  <div>
    <Head>
      <title>Ask Anything</title>
      <meta name="description" content="Ask anything" />
      {/* <link rel="icon" href="/favicon.ico" /> */}
    </Head>

    <main>
      <div css={homeStyle}>hello</div>
    </main>

    <footer>footer</footer>
  </div>
);

const homeStyle = css`
  background-color: red;
`;

export default Home;
