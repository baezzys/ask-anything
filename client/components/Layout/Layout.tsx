import { css } from '@emotion/react';
import { MOBILE_WIDTH } from 'const/style';
import React, { ReactNode } from 'react';
import colors from 'utils/style/colors';
import pxToRem from 'utils/style/pxToRem';
import Nav from './Molecule/Nav';

type LayoutProps = {
  children: ReactNode;
};

const Layout = ({ children }: LayoutProps) => (
  <div css={layoutContainer}>
    <div css={layout}>
      <Nav />
      {children}
    </div>
  </div>
);

const layoutContainer = css`
  background-color: ${colors.gray[100]};
`;

const layout = css`
  box-sizing: border-box;
  max-width: ${pxToRem(MOBILE_WIDTH)};
  min-height: 100vh;
  margin: 0 auto;
  background-color: white;
`;

export default Layout;
