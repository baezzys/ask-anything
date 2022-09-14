/* eslint-disable jsx-a11y/no-static-element-interactions */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import { css } from '@emotion/react';
import { ReactNode } from 'react';
import { createPortal } from 'react-dom';
import pxToRem from 'utils/style/pxToRem';
import getModalSubComponent from '../utils/getModalSubComponent';
import { ModalButtonType } from './ModalButton';
import { ModalTitleType } from './ModalTitle';

/** Main Component */
type ModalMainProps = {
  children?: ReactNode;
  isOpen: boolean;
  close: () => void;
};

const ModalMain = ({ children, isOpen, close }: ModalMainProps) => {
  if (!isOpen) {
    return null;
  }

  const title = getModalSubComponent(ModalTitleType, children);
  const buttons = getModalSubComponent(ModalButtonType, children);

  return createPortal(
    <div css={containerStyle}>
      <div onClick={close} css={dimStyle} />
      <div css={modalStyle}>
        {title}
        <div css={buttonsStyle}>{buttons}</div>
      </div>
    </div>,
    document.body,
  );
};

const buttonsStyle = css`
  button {
    margin-bottom: ${pxToRem(10)};
  }
  button:last-child {
    margin: 0;
  }
`;

const containerStyle = css`
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
`;

const modalStyle = css`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  max-width: ${pxToRem(350)};
  min-width: ${pxToRem(200)};
  border-radius: ${pxToRem(5)};

  padding: ${pxToRem(40)};
  background-color: white;
`;

const dimStyle = css`
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.1);
`;

export default ModalMain;
