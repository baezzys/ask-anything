import { css } from '@emotion/react';
import { ReactNode } from 'react';
import pxToRem from 'utils/style/pxToRem';
import colors from 'utils/style/colors';

/** Sub Component */

type ModalButtonProps = {
  children?: ReactNode;
  buttonType?: 'nomal' | 'kakao';
  onClick?: () => void;
  width?: string;
};

export const ModalButton = ({
  children,
  buttonType = 'nomal',
  onClick,
  width = '100%',
}: ModalButtonProps) => (
  <button type="button" onClick={onClick} css={buttonStyle(buttonType, width)}>
    {children}
  </button>
);
export const ModalButtonType = (<ModalButton />).type;

const buttonStyle = (buttonType: string, width: string) => css`
  border-radius: ${pxToRem(5)};
  width: ${width};
  padding: ${pxToRem(11)};

  ${buttonType === 'nomal'
    ? css`
        border: 1px solid ${colors.black[100]};
      `
    : ''}
  ${buttonType === 'kakao'
    ? css`
        background-color: ${colors.yellow[100]};
      `
    : ''};
`;
