import A11yHidden from 'components/common/A11yHidden';
import { useRouter } from 'next/router';
import { VscChevronLeft } from 'react-icons/vsc';

const LinkToBack = () => {
  const router = useRouter();

  return (
    <button type="button" title="뒤로가기" onClick={router.back}>
      <VscChevronLeft size={30} />
      <A11yHidden>뒤로가기</A11yHidden>
    </button>
  );
};

export default LinkToBack;
