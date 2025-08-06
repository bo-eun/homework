import { render, screen } from "@testing-library/react";
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import userEvent from "@testing-library/user-event";
import LoginExam from "../exam/LoginExam";

const user = userEvent.setup();

describe('로그인 테스트', () => {
    let alertMock;

    // 시작하기 전에 사전 처리
    beforeEach(() => {
        alertMock = vi.spyOn(window, 'alert').mockImplementation(() => { });
    })

    afterEach(() => {
        alertMock.mockRestore();
    })

    it('아이디, 비밀번호 입력 안한 상태에서 로그인 버튼 클릭 시', async () => {
        render(<LoginExam />);

        const idInput = screen.getByLabelText('아이디');
        const pwInput = screen.getByLabelText('비밀번호');
        const button = screen.getByRole('button', { name: '로그인' });

        await user.type(idInput, ' '); // 입력 값이 공백임
        await user.type(pwInput, ' '); // 입력 값이 공백임

        expect(idInput.value.trim().length).toBe(0); // 입력값이 없음
        expect(pwInput.value.trim().length).toBe(0);

        await user.click(button);

        expect(alertMock).toHaveBeenCalledWith('아이디와 비밀번호를 입력하세요');
    })

    it('아이디 입력 후 로그인 버튼 클릭 시', async () => {
        render(<LoginExam />);
        const idInput = screen.getByLabelText('아이디');
        const pwInput = screen.getByLabelText('비밀번호');
        const button = screen.getByRole('button', { name: '로그인' });

        await user.type(idInput, 'boeun123');
        await user.type(pwInput, ' ');
        expect(pwInput.value.trim().length).toBe(0); // 비밀번호 입력 값 없음

        await user.click(button);
        expect(alertMock).toHaveBeenCalledWith('비밀번호를 입력하세요');
    })

    it('비밀번호 입력 후 로그인 버튼 클릭 시', async () => {
        render(<LoginExam />);
        const idInput = screen.getByLabelText('아이디');
        const pwInput = screen.getByLabelText('비밀번호');
        const button = screen.getByRole('button', { name: '로그인' });

        await user.type(idInput, ' ');
        await user.type(pwInput, '123456');
        expect(idInput.value.trim().length).toBe(0); //  아이디 입력 값 없음

        await user.click(button);
        expect(alertMock).toHaveBeenCalledWith('아이디를 입력하세요');
    })

    it('아이디, 비밀번호 입력 후 로그인 버튼 클릭 시', async () => {
        render(<LoginExam />);
        const idInput = screen.getByLabelText('아이디');
        const pwInput = screen.getByLabelText('비밀번호');
        const button = screen.getByRole('button', { name: '로그인' });

        await user.type(idInput, 'boeun123');
        await user.type(pwInput, '123456');

        await user.click(button);
        expect(alertMock).toHaveBeenCalledWith('로그인 성공: boeun123');
    })
})