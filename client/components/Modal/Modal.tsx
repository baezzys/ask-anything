import { ModalButton } from './atom/ModalButton';
import ModalMain from './atom/ModalMain';
import { ModalTitle } from './atom/ModalTitle';

const Modal = Object.assign(ModalMain, {
  Title: ModalTitle,
  Button: ModalButton,
});

export default Modal;
