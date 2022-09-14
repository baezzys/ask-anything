import { css } from '@emotion/react';
import { ReactNode } from 'react';
import pxToRem from 'utils/style/pxToRem';

/** Sub Component */

type ModalTitleProps = {
  children?: ReactNode;
};

export const ModalTitle = ({ children }: ModalTitleProps) => (
  <div css={titleStyle}>{children}</div>
);
export const ModalTitleType = (<ModalTitle />).type;

const titleStyle = css`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin-bottom: ${pxToRem(20)};
`;
